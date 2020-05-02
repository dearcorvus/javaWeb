package com.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaweb.dao.UserDAO;
import com.javaweb.dao.factory.BaseFactory;
import com.javaweb.dao.factory.MD5Factory;
import com.javaweb.dao.impl.UserDAOJdbclmpl;
import com.javaweb.domain.User;


import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class UserServlet extends HttpServlet{

	/**
	 * 数据处理控件
	 */
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAOJdbclmpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath =  req.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);

		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
	
			method.invoke(this,req,resp);
		} catch (Exception e) {
//			e.printStackTrace();
			resp.sendRedirect("500.jsp");
		}
	}
	
	/**
	 * 用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void doUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			request.getRequestDispatcher("/view/adminUser.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void doPass(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			request.getRequestDispatcher("/view/adminPass.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	@SuppressWarnings("unused")
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		//编码
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
			
		String method = request.getParameter("method");
		
		if("logout".equals(method)) {
			logout(request,response);
			return;
		}
		
		
		//获取验证码判断
		String captcha = request.getParameter("captcha");
		//从session中获取
		HttpSession session = request.getSession();
		
		String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");//验证码只是用一次

		if(captcha == null || check_server ==null || !check_server.equalsIgnoreCase(captcha)) {
	        //list值存入json中
			String msg = BaseFactory.errorShow("验证码错误");
	        //输出jsoncode
	        out.print(msg);
            return;
			
		}	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDAO.get(username);

		if(user == null || !user.getUser_pass().equals(MD5Factory.md5(password))) {
				
			String msg = BaseFactory.errorShow("密码错误");
	        //输出jsoncode
	        out.print(msg);
            return;	
		}
		session.setAttribute("username", username);
		session.setAttribute("userId", user.getId());
		String msg = BaseFactory.successShow("登录成功","mainServlet");
        //输出jsoncode
        out.print(msg);
        return;	
		
	}
	//退出
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("userId");
		response.sendRedirect("mainServlet");
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	private void setPass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//编码
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String old_pass = request.getParameter("old_password");
		String password = request.getParameter("password");
		String repassword = request.getParameter("re_password");
		if("".equals(old_pass) || "".equals(password) || "".equals(repassword)) {
			String msg = BaseFactory.errorShow("密码错误！");
	        out.print(msg);
	        return;			
		}

		if(!password.equals(repassword)) {
			String msg = BaseFactory.errorShow("两次密码输入不同！");
	        out.print(msg);
	        return;				
		}
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Integer userid = (Integer)session.getAttribute("userId");
		
		User user = userDAO.get(userid);
		System.out.print(old_pass);
		System.out.print(user.getUser_pass());
		if(user == null || !user.getUser_login().equals(username) || !user.getUser_pass().equals(MD5Factory.md5(old_pass))) {
			String msg = BaseFactory.errorShow("原密码错误！");
	        out.print(msg);
	        return;		
		}
		
		User users = new User(username,password);
		System.out.print(users);				
		users.setId(userid);
		userDAO.update(users);
					
		String msg = BaseFactory.successShow("保存成功","");
        out.print(msg);
        return;	
		
	}
}

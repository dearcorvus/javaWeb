package com.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.javaweb.dao.UserDAO;
import com.javaweb.dao.factory.BaseFactory;
import com.javaweb.dao.impl.UserDAOJdbclmpl;
import com.javaweb.domain.User;

public class UserServlet extends HttpServlet{

	/**
	 * 数据处理控件
	 */
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAOJdbclmpl();
	
	private BaseFactory base = new BaseFactory();
	
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
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		
		
		//获取验证码判断
		String captcha = request.getParameter("captcha");
		//从session中获取
		HttpSession session = request.getSession();
		
		String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");//验证码只是用一次

		if(captcha == null || !check_server.equalsIgnoreCase(captcha)) {
            //验证码错误
			response.setContentType("application/json;charset=utf-8");
	        //list值存入json中
			PrintWriter out = response.getWriter();

			String msg = base.retuenShow("验证码错误", 0);
	        //输出jsoncode
	        System.out.println(msg);
	        out.print(msg);
            return;
			
		}	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		User user = userDAO.get(username);
		System.out.println(user);
//		User user = userDAO.get(username);
//		System.out.print("123");
//		System.out.println(user);
		
	}
}

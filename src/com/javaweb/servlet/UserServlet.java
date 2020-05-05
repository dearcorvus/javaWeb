package com.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 * ���ݴ���ؼ�
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
	 * �û���Ϣ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void doUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userId");
		User user = userDAO.get(userid);
		
		Date date =new Date();
		SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
		String str_date = dates.format(user.getBirthday());	

		try {
			if(user != null) {
				request.setAttribute("user", user);
				request.setAttribute("date", str_date);
				request.getRequestDispatcher("/view/adminUser.jsp").forward(request, response);		
			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �޸�����
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
		//����
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
			
		String method = request.getParameter("method");
		
		if("logout".equals(method)) {
			logout(request,response);
			return;
		}
		
		
		//��ȡ��֤���ж�
		String captcha = request.getParameter("captcha");
		//��session�л�ȡ
		HttpSession session = request.getSession();
		
		String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");//��֤��ֻ����һ��

		if(captcha == null || check_server ==null || !check_server.equalsIgnoreCase(captcha)) {
	        //listֵ����json��
			String msg = BaseFactory.errorShow("��֤�����");
	        //���jsoncode
	        out.print(msg);
            return;
			
		}	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDAO.get(username);

		if(user == null || !user.getUser_pass().equals(MD5Factory.md5(password))) {
				
			String msg = BaseFactory.errorShow("�������");
	        //���jsoncode
	        out.print(msg);
            return;	
		}
		session.setAttribute("username", username);
		session.setAttribute("userId", user.getId());
		String msg = BaseFactory.successShow("��¼�ɹ�","mainServlet");
        //���jsoncode
        out.print(msg);
        return;	
		
	}
	//�˳�
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("userId");
		response.sendRedirect("mainServlet");
	}
	
	/**
	 * �޸��û�����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	private void setPass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//����
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String old_pass = request.getParameter("old_password");
		String password = request.getParameter("password");
		String repassword = request.getParameter("re_password");
		if("".equals(old_pass) || "".equals(password) || "".equals(repassword)) {
			String msg = BaseFactory.errorShow("�������");
	        out.print(msg);
	        return;			
		}

		if(!password.equals(repassword)) {
			String msg = BaseFactory.errorShow("�����������벻ͬ��");
	        out.print(msg);
	        return;				
		}
		
		String username = (String)session.getAttribute("username");
		Integer userid = (Integer)session.getAttribute("userId");
		
		User user = userDAO.get(userid);

		if(user == null || !user.getUser_login().equals(username) || !user.getUser_pass().equals(MD5Factory.md5(old_pass))) {
			String msg = BaseFactory.errorShow("ԭ�������");
	        out.print(msg);
	        return;		
		}
		
		User users = new User(username,MD5Factory.md5(password));
				
		users.setId(userid);
		userDAO.update(users);
					
		String msg = BaseFactory.successShow("����ɹ�","");
        out.print(msg);
        return;	
	}
	
	@SuppressWarnings("unused")
	private void setUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//����
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String nickname = request.getParameter("user_nickname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String url = request.getParameter("user_url");
		String signature = request.getParameter("signature");
		Integer userid = (Integer)session.getAttribute("userId");
		
		//������תΪʱ���
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  

		 Date date = null;
		try {
			date = format.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 //����תʱ��������룩
		long time= date.getTime();
		
		User users = new User(nickname, sex, time, url, signature);
		users.setId(userid);
		userDAO.updates(users);
		
		String msg = BaseFactory.successShow("����ɹ�","");
        out.print(msg);
        return;			
		
	}
}

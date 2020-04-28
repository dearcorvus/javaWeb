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

public class UserServlet extends HttpServlet{

	/**
	 * ���ݴ���ؼ�
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

		if(captcha == null || !check_server.equalsIgnoreCase(captcha)) {
	        //listֵ����json��
			String msg = base.errorShow("��֤�����");
	        //���jsoncode
	        out.print(msg);
            return;
			
		}	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		User user = userDAO.get(username);

		if(user == null || !user.getUser_pass().equals(MD5Factory.md5(username))) {
				
			String msg = base.errorShow("�������");
	        //���jsoncode
	        out.print(msg);
            return;	
		}
		session.setAttribute("username", username);
		String msg = base.successShow("��¼�ɹ�","mainServlet");
        //���jsoncode
        out.print(msg);
        return;	
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("username");
		response.sendRedirect("mainServlet");
	}
}

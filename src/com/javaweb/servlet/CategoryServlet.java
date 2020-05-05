package com.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryServlet extends HttpServlet{

	/**
	 * ·ÖÀà
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath =  request.getServletPath();
		String methodName = servletPath.substring(1);		
		methodName = methodName.substring(0, methodName.length() - 4);
		try {
		
			if(methodName != null) {
				Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				
				method.invoke(this,request,response);		
			}
			

		} catch (Exception e) {
//			e.printStackTrace();
			response.sendRedirect("500.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unused")
	private void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/category.jsp").forward(request, response);
	}
	
	@SuppressWarnings("unused")
	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/addcategory.jsp").forward(request, response);

	}
}

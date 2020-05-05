package com.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/mainServlet")
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String servletPath =  request.getServletPath();
		String methodName = servletPath.substring(1);

		if("mainServlet".equalsIgnoreCase(methodName)) {	
			doPost(request, response);
			return;
		}
		
		methodName = methodName.substring(0, methodName.length() - 5);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		request.getSession().setAttribute("user",session.getAttribute("username"));
		request.getRequestDispatcher("/view/main.jsp").forward(request, response);
	}

	
	@SuppressWarnings("unused")
	private void mainPass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
	}
	
	@SuppressWarnings("unused")
	private void mainUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("doUser.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

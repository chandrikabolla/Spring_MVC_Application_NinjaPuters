package com.me.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class EmployeeInterceptor extends HandlerInterceptorAdapter{

	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		if(request.getMethod().equalsIgnoreCase("GET"))
		{
			if(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1).equalsIgnoreCase("logout"))
			{
				System.out.println("In interceptor: logout condition");
				response.addHeader("Cache-control","no-store,must-revalidate,private,no-cache");
				response.addHeader("Pragma","no-cache");
				response.addHeader("Expires","0");
			}
		if(session.getAttribute("employee") != null){
			System.out.println("in interceptor");
			return true;
		}
		
		System.out.println("no employee: as user");
		errorPage=request.getContextPath()+"/";
		response.sendRedirect(errorPage);
		return false;
		
		}
		else{
			return true;
		}
		
		
	}
}

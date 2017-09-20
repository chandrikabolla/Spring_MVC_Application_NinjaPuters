package com.me.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
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
			if(session.getAttribute("user")!=null)
			{
				if(session.getAttribute("admin") != null&&session.getAttribute("employee")==null){
					System.out.println("in interceptor");
					return true;
				}
				if(session.getAttribute("employee")!=null)
				{
					System.out.println("in interceptor");
					errorPage="/employee/homepage";
					System.out.println("no admin: as user but employee as user");
					response.sendRedirect(request.getContextPath()+errorPage);
					return false;
				}
			}
			
		
		
		System.out.println("request context path: "+request.getContextPath());
		System.out.println("no admin: as user and no employee: as user");
		response.sendRedirect(request.getContextPath()+"/"+errorPage);
		return false;
		}
		else{
			return true;
		}
		
		
		
		
	}

}

package com.me.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.dao.OrganizationDAO;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.KnowledgeChapter;
import com.me.myapp.pojo.Level;
import com.me.myapp.pojo.OrgImage;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;
import com.me.myapp.validator.AdminAccountValidator;
import com.me.myapp.validator.EmployeeValidator;

@Controller
public class AdminLoginController {

	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@Autowired
	@Qualifier("adminUserAccountValidator")
	AdminAccountValidator adminUAvalidator;
	
	
	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;
	
	@RequestMapping(value="/org/adminLogin",method=RequestMethod.GET)
	public ModelAndView getAdminLoginPage(HttpServletRequest request){
		//this is the function that returns admin-login jsp page view 
		ModelAndView mv=new ModelAndView();
		if(request.getSession().getAttribute("org")!=null)
		{
		mv.setViewName("admin-login");
		mv.addObject("adminUserAccount",new AdminUserAccount());
		}
		else{
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/admin/homepage",method=RequestMethod.GET)
	protected ModelAndView adminloginhomeGET(HttpServletRequest request) throws Exception {
	
		System.out.println("Method: "+request.getMethod());
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(session.getAttribute("org")==null)
		{
			mav.setViewName("redirect:/");
			return mav;
		}
		if(session.getAttribute("admin")==null)
		{
			mav.addObject("adminUserAccount", new AdminUserAccount());
			mav.addObject("loginfail", "LoginFirst");
			mav.setViewName("admin-login");
		}
		else{
			if(request.isRequestedSessionIdValid())
			{
				Admin admin=(Admin) session.getAttribute("admin");
				
				if(admin!=null&&admin.getName()!=null)
				{
					
					Organization org=admin.getOrg();
					
					// add org object to get roles from the org.roles in order to set the role of employeee to be registered
					mav.addObject("org", org);
					session.setAttribute("org",org);
					Organization organization=orgDao.getOrgByname(org.getOrgName());
					mav.addObject("roles", organization.getRoles());
					
					// an object initialized for creating a new role
					mav.addObject("empRole",new Role());
										
					// an object of role is to be declared into model in order to create a new role 
					mav.addObject("role",new Role());
					
					//a new knowledgeChapter object to first set the number of fileUploads for knowledgeChapter
					mav.addObject("knowledgeChapter",new KnowledgeChapter());
					
					//list of levels is needed to create a new role in the organization
					mav.addObject("levels",Level.getValues());
					
					//a new OrgImage object to allow admin to add a new photo of new occasion
					mav.addObject("orgImage",new OrgImage());
					
					
					mav.setViewName("admin-home");
				}
				else{
					mav.setViewName("admin-login");
					mav.addObject("adminUserAccount", new AdminUserAccount());
					mav.addObject("loginfail", "Wrong");
					return mav;
					
				}
			}
			else{
				mav.addObject("adminUserAccount", new AdminUserAccount());
				mav.addObject("loginfail", "LoginFirst");
				mav.setViewName("admin-login");
			}
		}
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/admin/homepage",method=RequestMethod.POST)
	protected ModelAndView adminloginhomePOST(HttpServletRequest request,  @ModelAttribute("adminUserAccount") AdminUserAccount adminUserAccount, BindingResult result) throws Exception {
		ModelAndView mv=new ModelAndView();
		System.out.println("Session post admin-home:"+request.getSession(false));
		if(request.getSession(false)!=null)
		{
		
			if(request.getSession().getAttribute("org")==null)
			{
				mv.setViewName("redirect:/");
				return mv;
			}
			System.out.println("Method: "+request.getMethod());
			adminUAvalidator.validate(adminUserAccount, result);
		
			if(result.hasErrors())
			{
				mv.setViewName("admin-login");
				mv.addObject("adminUserAccount", adminUserAccount);
				mv.addObject("loginfail", "Empty");
				return mv;
			}
			else{
		
				if(request.getSession().getAttribute("admin")==null||request.getAttribute("employee")==null)
				{
					AdminUserAccount adminuserAccount=null;
					HttpSession session=request.getSession(false);
					Admin reqAdmin =((Organization) session.getAttribute("org")).getAdmin();
					if(adminUserAccount.getUserName().equalsIgnoreCase(reqAdmin.getAdminUserAccount().getUserName())&&adminUserAccount.getPassword().equals(reqAdmin.getAdminUserAccount().getPassword()))
							{
								adminuserAccount=reqAdmin.getAdminUserAccount();
							}

				if(adminuserAccount!=null)
				{
					session=request.getSession();
					// set admin into session object
					session.setAttribute("admin", reqAdmin);
					session.setAttribute("user",reqAdmin);
					Organization org=reqAdmin.getOrg();
					// add org object to get roles from the org.roles in order to set the role of employeee to be registered
					mv.addObject("org", org);
					session.setAttribute("org",org);
					Organization organization=orgDao.getOrgByname(org.getOrgName());
					mv.addObject("roles", organization.getRoles());
					// an object initialized for creating a new role
					mv.addObject("empRole",new Role());
										
					// an object of role is to be declared into model in order to create a new role 
					mv.addObject("role",new Role());
					
					//a new knowledgeChapter object to first set the number of fileUploads for knowledgeChapter
					mv.addObject("knowledgeChapter",new KnowledgeChapter());
					
					//list of levels is needed to create a new role in the organization
					mv.addObject("levels",Level.getValues());
					
					//a new OrgImage object to allow admin to add a new photo of new occasion
					mv.addObject("orgImage",new OrgImage());
					
					
					mv.setViewName("admin-home");
				}
				else{
						mv.setViewName("admin-login");
						mv.addObject("adminUserAccount", new AdminUserAccount());
						mv.addObject("loginfail", "Wrong");
						return mv;
				}
				}
			}
		}
		else{
			mv.setViewName("redirect:/");
		}		
		return mv;
	
	}
}

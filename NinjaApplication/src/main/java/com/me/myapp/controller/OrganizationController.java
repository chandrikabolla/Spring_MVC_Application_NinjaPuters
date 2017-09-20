package com.me.myapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.dao.DAO;
import com.me.myapp.dao.EmployeeDAO;
import com.me.myapp.dao.OrganizationDAO;
import com.me.myapp.dao.RoleDAO;
import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Address;
import com.me.myapp.pojo.AddressType;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Email;
import com.me.myapp.pojo.EmailType;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.EmployeeUserAccount;
import com.me.myapp.pojo.KnowledgeChapter;
import com.me.myapp.pojo.Level;
import com.me.myapp.pojo.OrgImage;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;
import com.me.myapp.validator.AdminAccountValidator;
import com.me.myapp.validator.AdminRegisterValidator;
import com.me.myapp.validator.EmployeeValidator;
import com.me.myapp.validator.OrganizationValidator;



@Controller
public class OrganizationController {

	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;
	
	
	@Autowired
	@Qualifier("employeeValidator")
	EmployeeValidator validator;
	
	@Autowired
	@Qualifier("adminUserAccountValidator")
	AdminAccountValidator adminUAvalidator;

	@Autowired
	@Qualifier("organizationValidator")
	OrganizationValidator organizationValidator;
	
	@Autowired
	@Qualifier("adminRegisterValidator")
	AdminRegisterValidator adminRegisterValidator;
	
	@Autowired
	@Qualifier("empDao")
	EmployeeDAO empDao;
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@Autowired
	@Qualifier("roleDao")
	RoleDAO roleDao;
	
	@RequestMapping(value="add.htm",method=RequestMethod.GET)
	
	public ModelAndView getHomePage(){
		
		return new ModelAndView("home");
	}
	

	
	
	
	@RequestMapping(value="/org/employeeLogin",method=RequestMethod.GET)
	public ModelAndView getEmployeeLoginPage(HttpServletRequest req){
		//this is the function that returns admin-login jsp page view 
		ModelAndView mv=new ModelAndView();
		HttpSession session=req.getSession();
		if(session.getAttribute("org")!=null)
		{
		mv.setViewName("employee-login");
		mv.addObject("employeeUserAccount",new EmployeeUserAccount());
		mv.addObject("employee",new Employee());
		}
		else{
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/org/downloadPDF",method=RequestMethod.GET)
	public ModelAndView getPdfView(HttpServletRequest request) throws EmployeeException, OrganizationException{
	
		ModelAndView mv=new ModelAndView(new PDFBuilder());	
		HttpSession session=request.getSession();
		Organization org=(Organization) session.getAttribute("org");
		System.out.println("Organization in the session is:"+ org.getOrgName());
		List<Employee> employees=orgDao.getEmployees(org.getOrgName());
		mv.addObject("employees",employees);
		
		return mv;
	}
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public ModelAndView showErrorPage()
	{
		ModelAndView mv=new ModelAndView("index");
		mv.addObject("employee", new Employee());
		return mv;
	}
	
	
	@RequestMapping(value={"/","/orgSelectionPage"},method=RequestMethod.GET)
	
	public ModelAndView gethomePage(HttpServletRequest req) throws OrganizationException, EmployeeException, RoleException{

		ModelAndView mv=new ModelAndView();
		ArrayList<Organization> orgs=orgDao.listOfOrgs();
		if(req.getSession(false)!=null)
		{
			System.out.println("Alert: Session still exists");
			HttpSession session=req.getSession();
			session.invalidate();
			System.out.println("Session: Creating a new session");
			session=req.getSession(true);
			if(session.getAttribute("admin")==null&&session.getAttribute("employee")==null)
			{
				System.out.println(" / " +" There is no admin and employee in session object");
			}
			else{
				
				if(session.getAttribute("admin")!=null)
				{
					System.out.println(" / "+ " Admin exists in Session object");
					return new ModelAndView("redirect:/admin/homepage");
				}
				if(session.getAttribute("employee")!=null)
				{
					System.out.println(" / "+ "Employee exists in Session object");
					return new ModelAndView("redirect:/employee/homepage");
				}
				
			}
			
			if(req.getSession().getAttribute("org")==null)
			{
				System.out.println(" / "+" There is no organization in session object");
				mv.addObject("orgsSize",orgs.size());
				mv.addObject("orgs",orgs);
				mv.addObject("org",new Organization());
				mv.addObject("newOrg", new Organization());
				mv.setViewName("home");
			}
			else{
				mv.setViewName("index");
			}
		}
		else{
			HttpSession session=req.getSession(true);
			mv.addObject("orgs",orgs);
			mv.setViewName("home");
			mv.addObject("org", new Organization());
			mv.addObject("newOrg", new Organization());
		
		}
		return mv;
	}
	
	@RequestMapping(value="/org",method=RequestMethod.GET)
	public ModelAndView getindexPageGET(HttpServletRequest req) throws OrganizationException, EmployeeException, RoleException{

		ModelAndView mv=new ModelAndView("index");
		HttpSession session=req.getSession();
		Organization org=(Organization) session.getAttribute("org");
		// if org exists in session display ****index**** page
		if(org!=null)
		{
			Organization organization=orgDao.getOrgByname(org.getOrgName());
			System.out.println("Required admin name: "+organization.getAdmin());
			if(organization!=null)
			{
			
			
				
				System.out.println(organization.getAdmin().getName()+" - username: "+organization.getAdmin().getAdminUserAccount().getUserName() +" - password: "+organization.getAdmin().getAdminUserAccount().getPassword());
				session.setAttribute("reqAdmin",org.getAdmin());
				mv.addObject("reqAdminExists","EXISTS");
				mv.addObject("NumOrgImages",organization.getOrgIamges().size());
				if(organization.getOrgIamges().size()>0)
				{
					System.out.println("Greater than 0: "+organization.getOrgIamges().size());
					ArrayList<OrgImage> imageList=new ArrayList<OrgImage>();
					
				}
				mv.addObject("ImageList", organization.getOrgIamges());
				mv.addObject("Num",organization.getOrgIamges().size()-1);
				mv.addObject("org",organization);
				session.setAttribute("org",organization);
				mv.setViewName("index");
			}
		
		}
		// if no org exists in session object display ****home**** page
		else{
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@RequestMapping(value="/org",method=RequestMethod.POST)
	public ModelAndView getindexPage(HttpServletRequest req,@ModelAttribute("org")Organization org) throws OrganizationException, EmployeeException, RoleException{

		ModelAndView mv=new ModelAndView("index");
		HttpSession session=req.getSession();
		if(org!=null)
		{
			Organization organization=orgDao.getOrgByname(org.getOrgName());
			System.out.println("Required admin name: "+organization.getAdmin());
			if(organization!=null)
			{
			
			
				
				System.out.println(organization.getAdmin().getName()+" - username: "+organization.getAdmin().getAdminUserAccount().getUserName() +" - password: "+organization.getAdmin().getAdminUserAccount().getPassword());
				session.setAttribute("reqAdmin",org.getAdmin());
				mv.addObject("reqAdminExists","EXISTS");
				mv.addObject("NumOrgImages",organization.getOrgIamges().size());
				if(organization.getOrgIamges().size()>0)
				{
					System.out.println("Greater than 0: "+organization.getOrgIamges().size());
					ArrayList<OrgImage> imageList=new ArrayList<OrgImage>();
					
				}
				mv.addObject("ImageList", organization.getOrgIamges());
				mv.addObject("Num",organization.getOrgIamges().size()-1);
				mv.addObject("org",organization);
				session.setAttribute("org",organization);
				mv.setViewName("index");
				
				
			}
			else{
				mv.setViewName("redirect:/");
			}
			
		}
		else{
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}

	
	@RequestMapping(value="/org/Register",method=RequestMethod.POST)
	public ModelAndView getToHomeAgain(HttpServletRequest request,@ModelAttribute("newOrg")Organization newOrg,BindingResult orgResult,@ModelAttribute("admin")Admin admin,BindingResult adminResult) throws OrganizationException, EmployeeException
	{
		ModelAndView mv=new ModelAndView("redirect:/");
		organizationValidator.validate(newOrg,orgResult);
		adminRegisterValidator.validate(admin,adminResult);
		if(orgResult.hasErrors())
		{
			
			
		}
		else if(!orgResult.hasErrors()){
			
				Admin reAdmin=adminDao.getadminByName(admin.getName());
				System.out.println("Readmin: "+reAdmin);
				if(reAdmin!=null)
				{
					adminResult.rejectValue("name","error.admin","This admin name has already been taken.Choose another name");
				}
				else{
					
				}
			Organization org=orgDao.getOrgByname(newOrg.getOrgName());
			if(org!=null)
			{
				orgResult.rejectValue("orgName", "error.newOrg", "An Org already exists.");
				
			}
			
		}
		if(orgResult.hasErrors()||adminResult.hasErrors())
		{
		mv.setViewName("org-register");
		mv.addObject("newOrg",newOrg);
		mv.addObject("admin",admin);
		return mv;
		}
		System.out.println("Org: "+newOrg.getOrgName());
		System.out.println("Admin: "+admin.getName() +" Email: "+admin.getAdminEmail());
		admin.setOrg(newOrg);
		AdminUserAccount aua=UserAccountCreator.createAdminUserAccount(admin.getName());
		
		admin.setAdminUserAccount(aua);
		aua.setAdmin(admin);
		EmailController.sendEmail(admin.getAdminEmail(), admin);
		newOrg.setAdmin(admin);
		
		Organization orgn=orgDao.register(newOrg);
		System.out.println("Organization saved: \n"+orgn.getOrg_id()+" - "+orgn.getOrgName()+" - "+orgn.getAdmin()+" -  "+orgn.getAdmin().getAdminUserAccount().getUserName()+ "-"+orgn.getAdmin().getAdminUserAccount().getPassword());
		return mv;
	}
	
	@RequestMapping(value="/org/Register",method=RequestMethod.GET)
	public ModelAndView getRegistrationPage()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("org-register");
		mv.addObject("newOrg",new Organization());
		mv.addObject("admin", new Admin());
		
		return mv;
	}
}

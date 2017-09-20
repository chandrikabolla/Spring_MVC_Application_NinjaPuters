package com.me.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.dao.AnnouncementDAO;
import com.me.myapp.dao.ContactDAO;
import com.me.myapp.dao.EmployeeDAO;
import com.me.myapp.dao.OrgImageDAO;
import com.me.myapp.dao.OrganizationDAO;
import com.me.myapp.dao.RoleDAO;
import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Address;
import com.me.myapp.pojo.AddressType;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Announcement;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Email;
import com.me.myapp.pojo.EmailType;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.KnowledgeChapter;
import com.me.myapp.pojo.OrgImage;
import com.me.myapp.pojo.Organization;
import com.me.myapp.validator.AdminAccountValidator;
import com.me.myapp.validator.EmployeeLoginValidator;
import com.me.myapp.validator.EmployeeValidator;


@Controller
public class EmployeeController {

	HttpSession session;
	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;
	
	@Autowired
	@Qualifier("contactDao")
	ContactDAO contactDao;

	@Autowired
	@Qualifier("annDao")
	AnnouncementDAO annDao;
	
	@Autowired
	@Qualifier("orgImageDao")
	OrgImageDAO orgImageDao;
	
	
	@Autowired
	@Qualifier("employeeLoginValidator")
	EmployeeLoginValidator employeeLoginvalidator;
	
	@Autowired
	@Qualifier("adminUserAccountValidator")
	AdminAccountValidator adminUAvalidator;

	
	@Autowired
	@Qualifier("empDao")
	EmployeeDAO empDao;
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@Autowired
	@Qualifier("roleDao")
	RoleDAO roleDao;
	
	@RequestMapping(value="/employee/homepage",method=RequestMethod.GET)
	protected ModelAndView adminloginhomeGET(HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		
		
		
		Employee employee=(Employee) session.getAttribute("employee");
		
		Employee emp=empDao.getEmployeeById(employee.getEmpid());
		System.out.println("Employee received: "+employee.getEmpid()+"\n"+employee.getEmployeeUserAccount().getUserName()+"----"+employee.getEmployeeUserAccount().getPassword());
	//	System.out.println("Employee retrieved: " +emp.getEmpid() +"\n "+emp.getEmployeeUserAccount().getUserName()+"-----"+emp.getEmployeeUserAccount().getPassword());
		if(emp!=null&&emp.getEmployeeUserAccount().getUserName().equalsIgnoreCase(employee.getEmployeeUserAccount().getUserName())&&emp.getEmployeeUserAccount().getPassword().equals(employee.getEmployeeUserAccount().getPassword()))
		{
			session.setAttribute("org",emp.getOrg());
			session.setAttribute("employee",emp);
			session.setAttribute("user",emp);
			mav.addObject("org", emp.getOrg());
			mav.addObject("employee",emp);
			mav.setViewName("employee-homepage");
		}
		
		
		
		return mav;
		
	}
	@RequestMapping(value="/employee/homepage",method=RequestMethod.POST)
	protected ModelAndView adminloginhomePOST(HttpServletRequest request,  @ModelAttribute("employee") Employee employee, BindingResult result) throws Exception {
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		
		employeeLoginvalidator.validate(employee, result);

		if(result.hasErrors())
		{
			mav.setViewName("employee-login");
			mav.addObject("loginfail", "Empty");
			mav.addObject("employee", employee);
			return mav;
		}
		Employee emp=empDao.getEmployeeById(employee.getEmpid());
		System.out.println("Employee received: "+employee.getEmpid()+"\n"+employee.getEmployeeUserAccount().getUserName()+"----"+employee.getEmployeeUserAccount().getPassword());
	//	System.out.println("Employee retrieved: " +emp.getEmpid() +"\n "+emp.getEmployeeUserAccount().getUserName()+"-----"+emp.getEmployeeUserAccount().getPassword());
		if(emp!=null&&emp.getEmployeeUserAccount().getUserName().equalsIgnoreCase(employee.getEmployeeUserAccount().getUserName())&&emp.getEmployeeUserAccount().getPassword().equals(employee.getEmployeeUserAccount().getPassword()))
		{
			Organization org=(Organization) session.getAttribute("org");
			if(org!=null)
			{
				if(emp.getOrg().getOrgName().equalsIgnoreCase(org.getOrgName())){
					session.setAttribute("employee",emp);
					session.setAttribute("user",emp);
					mav.addObject("org", emp.getOrg());
					mav.addObject("employee",emp);
					mav.setViewName("employee-homepage");
				}
			}
			else{
				mav.setViewName("redirect:/");
			}
			
		}
		else{
			
			mav.setViewName("employee-login");
			mav.addObject("loginfail", "Wrong");
			mav.addObject("employee", employee);
			return mav;
		}
		
		
		return mav;
		
	}
	
	@RequestMapping(value="/orgPhotos",method=RequestMethod.GET)
	public ModelAndView getAlbumPge(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session.getAttribute("org")!=null)
		{
			Organization org=(Organization) session.getAttribute("org");
			int numOrgImages=org.getOrgIamges().size();
			ArrayList<OrgImage> orgImages=orgImageDao.getOrgAlbum(org.getOrgName());
		ModelAndView mv=new ModelAndView("album");
		mv.addObject("NumOrgImages",numOrgImages);
		mv.addObject("orgImages",orgImages);
		return mv;
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/announcements",method=RequestMethod.GET)
	public ModelAndView getframea(HttpServletRequest request,@QueryParam("page") int page){
		ModelAndView mv=new ModelAndView();
		
		HttpSession session=request.getSession();
		Organization org=(Organization) session.getAttribute("org");
		int from=(page-1)*5;
		int max=5;
		int total=org.getAnnouncements().size();
		int endSize;
		if(total%5==0)
		{
			endSize=total/5;
		}
		else{
			
			endSize=(total/5)+1;
		}
		if(page==endSize)
		{
			max=total%5;
		}
		if(org!=null)
		{
			System.out.println("page: "+page +" from: "+from+" max:"+max);
			List<Announcement> annList=annDao.listAnnouncements(org.getOrgName(),from, max);
			mv.addObject("annList",annList);
			
			
			mv.addObject("endSize",endSize);
			System.out.println("annList size"+ annList.size());
			mv.addObject("size",annList.size());
			mv.setViewName("org-announcements");
			
		}
		
		
		return mv;
	}
	
	@RequestMapping(value="/announcementsajax",method=RequestMethod.GET)
	public ModelAndView getajax(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Organization org=(Organization) session.getAttribute("org");
		if(org!=null)
		{
			List<Announcement> annList=org.getAnnouncements();
			mv.addObject("annList",annList);
			System.out.println("ajax: annList size"+ annList.size());
			mv.addObject("size",annList.size());
			mv.setViewName("org-announcements");
			
			
		}
		
		
		return null;
	}
	@RequestMapping(value="/addContact/{contactname}",method=RequestMethod.GET)
	public ModelAndView addContactToAddressBook(HttpServletRequest request,@PathVariable("contactname") String contactname) throws EmployeeException, RoleException, OrganizationException
	{
		ModelAndView mv;
		System.out.println("Contact name is: "+contactname);
		Contact contact=contactDao.getContactByName(contactname);
		if(contact!=null)
		{
		if(request.getSession(false)!=null)
		{
			HttpSession session=request.getSession();
			Employee employee=(Employee) session.getAttribute("employee");
			if(employee!=null)
			{
				System.out.println("Employee is not null");
				Employee emp=empDao.getEmployeeById(employee.getEmpid());
				if(emp.getContacts().contains(contact))
				{
					
				}
				else{
					emp.getContacts().add(contact);
					empDao.update(emp);
					emp=empDao.getEmployeeById(emp.getEmpid());
					session.setAttribute("employee",emp);
					System.out.println("Contact added successfully: "+emp.getContacts().size());
				}
				
				
			}
			else{
				
			}
		}
		}
		mv=new ModelAndView("redirect:/myContacts");
		return mv;
		
	}
	@RequestMapping(value="/myContacts",method=RequestMethod.GET)
	public ModelAndView getMyContactsPage(HttpServletRequest request) throws EmployeeException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Employee employee=(Employee) session.getAttribute("employee");
		
		if(employee!=null)
		{
			Employee emp=empDao.getEmployeeById(employee.getEmpid());
			if(emp!=null)
			{
			mv.addObject("employee", emp);
			List<Contact> contacts=emp.getContacts();
			mv.addObject("contactListSize",contacts.size());
			mv.addObject("contacts", contacts);
			mv.setViewName("employee-mycontacts");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/myProfile",method=RequestMethod.GET)
	public ModelAndView getProfilePage(HttpServletRequest request) throws EmployeeException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Employee emp=(Employee) session.getAttribute("employee");
		Employee employee=empDao.getEmployeeById(emp.getEmpid());
		Address permanent=new Address();
		Address temporary=new Address();
		Email personal=new Email();
		Email company=new Email();
		if(employee!=null){
			if(employee.getAddresses().size()!=0)
			{
				for(Address address:employee.getAddresses())
				{
					if(address.getAddresstype().equals(AddressType.Permanent))
					{
						permanent=address;
					}
					else{
						temporary=address;
					}
				}
			}
			employee.setPermanentAddress(permanent);
			employee.setTemporaryAddress(temporary);
			if(employee.getEmailids().size()!=0)
			{
				for(Email email:employee.getEmailids())
				{
					if(email.getEmailtype().equals(EmailType.PersonalEmailId))
					{
						personal=email;
					}
					else{
						company=email;
					}
				}
			}
			employee.setPersonalEmailID(personal);
			employee.setCompanyEmailID(company);
			mv.addObject("employee",employee);
		mv.setViewName("employee-myProfile");
		}
		
		return mv;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/employee/logout",method=RequestMethod.GET)
	public ModelAndView getIndexPageAfterLogout(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		
		HttpSession session=request.getSession();
		session.removeAttribute("employee");
		session.removeValue("employee");
		session.setMaxInactiveInterval(0);
		session.invalidate();
		mv.setViewName("index");
		return new ModelAndView("redirect:/");
		
	}
	
	@RequestMapping(value="/employee/updateEmpProfile",method=RequestMethod.POST)
	public ModelAndView updateProfile(HttpServletRequest request,@ModelAttribute("employee")Employee employee) throws EmployeeException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Employee emp=(Employee) session.getAttribute("employee");
		System.out.println("Current session employee in:"+emp.getOrg());
		employee.setOrg(emp.getOrg());
		if(emp!=null)
		{
		mv.addObject("employee",employee);
		empDao.update(employee);
		mv.setViewName("employee-myProfile");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/knowledgeHeap",method=RequestMethod.GET)
	public ModelAndView getKnowledgeHeapView(HttpServletRequest request) throws EmployeeException, OrganizationException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Organization org=(Organization)session.getAttribute("org");
		Organization organization=orgDao.getOrgByname(org.getOrgName());
		List<KnowledgeChapter> kclist= organization.getKnowledgeHeap();
		mv.addObject("kclist",kclist);
		mv.addObject("chapters",kclist.size());
		mv.setViewName("org-knowledgeHeap");
		
		
		return mv;
	}
}

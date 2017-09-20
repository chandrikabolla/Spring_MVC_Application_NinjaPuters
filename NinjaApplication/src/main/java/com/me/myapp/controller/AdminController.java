package com.me.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import static com.me.myapp.dao.DAO.getSession;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.dao.AnnouncementDAO;
import com.me.myapp.dao.ContactDAO;
import com.me.myapp.dao.EmployeeDAO;
import com.me.myapp.dao.KnowledgeChapterDAO;
import com.me.myapp.dao.OrgImageDAO;
import com.me.myapp.dao.OrganizationDAO;
import com.me.myapp.dao.RoleDAO;
import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrgImageException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Address;
import com.me.myapp.pojo.AddressType;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Announcement;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Email;
import com.me.myapp.pojo.EmailType;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.EmployeeUserAccount;
import com.me.myapp.pojo.FileUpload;
import com.me.myapp.pojo.KnowledgeChapter;
import com.me.myapp.pojo.Level;
import com.me.myapp.pojo.OrgImage;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;
import com.me.myapp.validator.AdminAccountValidator;
import com.me.myapp.validator.EmployeeValidator;


import javax.persistence.Lob;

import java.sql.Blob;

@Controller
public class AdminController {

	
	HttpSession session;
	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;
	
	@Autowired
	@Qualifier("annDao")
	AnnouncementDAO annDao;
	
	@Autowired
	@Qualifier("contactDao")
	ContactDAO contactDao;
	
	@Autowired
	@Qualifier("orgImageDao")
	OrgImageDAO orgImageDao;
	
	
	@Autowired
	@Qualifier("employeeValidator")
	EmployeeValidator validator;
	
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
	
	@Autowired
	@Qualifier("knowledgeChapterDao")
	KnowledgeChapterDAO knowledgeChapterDao;
	

	

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/admin/logout",method=RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("home");
		HttpSession session=request.getSession();
		
		Admin admin=(Admin) session.getAttribute("admin");
		if(admin!=null)
		{
			//session.removeAttribute("admin");
			Enumeration< String> attnames=session.getAttributeNames();
			while(attnames.hasMoreElements())
			{
				String attname=attnames.nextElement();
				System.out.println("attname:" +attname);
				session.removeAttribute(attname);
				
			}
					
		}
		session.removeAttribute("admin");
		session.removeValue("employee");
		session.setMaxInactiveInterval(0);
		session.invalidate();
		
		return new ModelAndView("redirect:/");
	}
	
	
	
	@RequestMapping(value="/admin/registeremployee",method=RequestMethod.POST)
	protected ModelAndView adminloginregisterEmployeePOST(HttpServletRequest request,  @ModelAttribute("empRole") Role empRole, BindingResult result) throws Exception {
	
		if(empRole.getRoleName()!=null)
		{
		Role role=roleDao.getRoleByName(empRole.getRoleName());
		Employee employee=new Employee();
		employee.setEmpRole(role);
		System.out.println("level of role:"+role.getLevel().getValue());
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		session.setAttribute("role", role);
		if(session.getAttribute("admin")==null)
		{
			mav.addObject("adminUserAccount", new AdminUserAccount());
			mav.addObject("loginfail", "LoginFirst");
			mav.setViewName("admin-login");
		}
		else{
			if(employee.getEmpRole()!=null)
			{			
			mav.addObject("employee",employee);
			mav.setViewName("admin-registerEmployee");
			}
			else{
			mav.setViewName("admin-home");
			}
		}
		
		return mav;
		}
		else{
			ModelAndView mv = new ModelAndView();
			Admin admin=(Admin) request.getSession().getAttribute("admin");
			if(admin!=null)
			{
				mv=new ModelAndView("redirect:/admin/homepage");
				mv.addObject("adminUserAccount",admin.getAdminUserAccount());
				
			}
			else{
				mv.setViewName("redirect:/");
			}
			
			return mv;
		}
	
	}
	@RequestMapping(value="/admin/registeremployee",method=RequestMethod.GET)
	protected ModelAndView adminloginRegisterEmployeeGET(HttpServletRequest request,  @ModelAttribute("employee") Employee employee, BindingResult result) throws Exception {
	
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(session.getAttribute("admin")==null)
		{
			mav.addObject("adminUserAccount", new AdminUserAccount());
			mav.addObject("loginfail", "LoginFirst");
			mav.setViewName("admin-login");
		}
		else{
			mav.addObject("employee", new Employee());
			mav.setViewName("admin-registerEmployee");
		}
		
		return mav;
	
	}
	
	@RequestMapping(value="/org/addKnowledgeChapter",method=RequestMethod.POST)
	public ModelAndView uploadForm(HttpServletRequest request,@ModelAttribute("knowledgeChapter") KnowledgeChapter knowledgeChapter)
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		
		if(admin!=null){
			session.setAttribute("org",admin.getOrg());
			List<FileUpload> fileUploads=new ArrayList<FileUpload>();
			for(int i=0;i<knowledgeChapter.getNumOffiles();i++)
			{
				FileUpload fu=new FileUpload();
				fu.setFilename("filename"+i);
				//fu.setFile(new CommonsMultipartFile(null));
				fileUploads.add(fu);
				knowledgeChapter.getFileUploads().add(fu);
			}
			
			mv.addObject("filenumber",knowledgeChapter.getFileUploads().size()-1);
			mv.addObject("knowledgeChapter",knowledgeChapter);
			mv.setViewName("uploadForm");
		}
		else{
			mv.addObject("adminUserAccount", new AdminUserAccount());
			mv.addObject("loginfail", "LoginFirst");
			mv.setViewName("admin-login");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/org/uploadChapter", method = RequestMethod.POST)
	public String handleUpload(@ModelAttribute("knowledgeChapter") KnowledgeChapter knowledgeChapter,HttpServletRequest request) throws OrganizationException {
		List<String> filenames=new ArrayList<String>();
		List<FileUpload> fileUploads=new ArrayList<FileUpload>();
		try {
			HttpSession session=request.getSession();
			Organization org=(Organization)session.getAttribute("org");
			for(FileUpload fileUpload:knowledgeChapter.getFileUploads()){
				String filename=fileUpload.getFilename();
			if (filename.trim() != "" || filename!= null) {
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "\\"+ filename);
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					
					CommonsMultipartFile photoInMemory = fileUpload.getFile();

					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					filenames.add(localFile.getPath());
				    String fileString=filename+"_"+fileName.substring(fileName.lastIndexOf("/") + 1);
				    String newFileString=fileString.replaceAll(".pdf", "");
					FileUpload newFileUpload=new FileUpload();
					newFileUpload.setFilename(localFile.getPath());
					newFileUpload.setFileString(newFileString);
					newFileUpload.setKnowledgeChapter(knowledgeChapter);
					fileUploads.add(newFileUpload);
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					}
					

				} else {
					System.out.println("Failed to create directory!");
				}
			}
			
			for(String filenam:filenames)
			{
				System.out.print(" ---- "+filenam);
			}
			knowledgeChapter.setFileUploads(fileUploads);
			//org.getKnowledgeHeap().add(knowledgeChapter);
			knowledgeChapter.setOrg(org);
			org.getKnowledgeHeap().add(knowledgeChapter);
			knowledgeChapterDao.register(knowledgeChapter);
			//orgDao.update(org);

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		}

		return "upload-success";
	}
	
	@RequestMapping(value="/admin/registerOrgRole",method=RequestMethod.POST)
	public ModelAndView showAddRolePagePOST(HttpServletRequest request,@ModelAttribute("role") Role role){
		ModelAndView mv=new ModelAndView();
		if(request.getSession().getAttribute("admin")!=null)
		{
			if(role.getLevel()!=null)
			{
				mv.addObject("role",role);
				mv.setViewName("admin-addrole");
			}
			else if(role.getLevel()==null){
				mv.setViewName("admin-home");
			}
		}
		else{
			mv.setViewName("redirect:/org/adminLogin");
		}
		
		
		return mv;
		
	}
	
	@RequestMapping(value="/admin/registerOrgRole",method=RequestMethod.GET)
	public ModelAndView showAddRolePageGET(HttpServletRequest request,@ModelAttribute("role") Role role){
		ModelAndView mv=new ModelAndView();
		if(request.getSession().getAttribute("admin")!=null)
		{
			mv.setViewName("redirect:/admin/homepage");
		}
		else{
		mv.setViewName("redirect:/org/adminLogin");
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/admin/addnewRole",method=RequestMethod.POST)
	public ModelAndView addRoleTodb(HttpServletRequest request,@ModelAttribute("role")Role role,BindingResult roleResult) throws OrganizationException, RoleException, EmployeeException
	{
		ModelAndView mv=new ModelAndView();
		
		HttpSession session=request.getSession();
		Organization org=(Organization)session.getAttribute("org");
		if(org!=null)
		{
			if(role.getLevel()!=null&&role.getRoleName()!=null)
			{
				boolean roleExists=false;
				Organization orgn=orgDao.getOrgByname(org.getOrgName());
				for(Role eachRole:orgn.getRoles())
				{
					if(eachRole.getRoleName().equalsIgnoreCase(role.getRoleName())&&eachRole.getLevel().equals(role.getLevel()))
					{
						roleExists=true;
					}
				}
				
					if(!roleExists)
					{
					
					role.setOrg(orgn);
					org.getRoles().add(role);
					roleDao.register(role);				
					mv.addObject("roleadded","SUCCESS");
					mv.setViewName("redirect:/admin/homepage");
					}
					else{
						roleResult.rejectValue("roleName","error.role","This role already exists in your organization in level: "+role.getLevel());
						mv.addObject("role",role);
						mv.setViewName("admin-addrole");
					
					}
			}
			else{
				mv.addObject("roleadded","FAILURE");
				mv.setViewName("redirect:/admin/homepage");
			}
		}
		else{
			mv.setViewName("redirect:/");
		}
		
		
		return mv;
	}
	
	@RequestMapping(value="/admin/addnewRole",method=RequestMethod.GET)
	public ModelAndView addRoleTodbGET(HttpServletRequest request,@ModelAttribute("role")Role role) throws OrganizationException, RoleException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Organization org=(Organization)session.getAttribute("org");
		if(org!=null)
		{
				if(role!=null)
				{
				role.setOrg(org);
				roleDao.register(role);
				//org.getRoles().add(role);
				mv.addObject("roleadded","SUCCESS");
				mv.setViewName("redirect:/admin/homepage");
				}
				else{
					mv.addObject("roleadded","FAILURE");
					mv.setViewName("redirect:/admin/homepage");
				}
		}
		else{
				mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/org/makeAnnocuncement",method=RequestMethod.GET)
	public ModelAndView getAnnouncmentFormPage(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView();
		
		mv.addObject("announcement",new Announcement());
		mv.setViewName("admin-orgAnnouncement");
		
		return mv;
	}
	
	@RequestMapping(value="/org/makeNewAnnouncement",method=RequestMethod.POST)
	public ModelAndView makeAnnouncement(HttpServletRequest request,@ModelAttribute("announcement")Announcement announcement) throws EmployeeException, RoleException, OrganizationException{
		
		HttpSession session=request.getSession();
		Organization org=(Organization) session.getAttribute("org");
		ModelAndView mv=new ModelAndView();
		if(announcement!=null)
		{
			announcement.setOrg(org);
			org.getAnnouncements().add(announcement);
			annDao.register(announcement);
		}
		
		mv.setViewName("redirect:/admin/homepage");
		
		
		return mv;
		
	}
	
	@RequestMapping(value="/org/saveNewEmployee",method=RequestMethod.POST)
	public ModelAndView saveNewEmployee(HttpServletRequest request,@ModelAttribute("employee") Employee employee,BindingResult resultEmployee) throws EmployeeException, RoleException, OrganizationException
	{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		Organization org=(Organization)session.getAttribute("org");
		
		//set role and level
		Role role=(Role)session.getAttribute("role");
		employee.setEmpRole(role);
		employee.setLevel(role.getLevel());	
		
		//set emailids
		Email personalEmail=employee.getPersonalEmailID();
		personalEmail.setEmployee(employee);
		personalEmail.setEmailtype(EmailType.PersonalEmailId);
		employee.getEmailids().add(personalEmail);
		
		Email companyEmail=employee.getCompanyEmailID();
		companyEmail.setEmployee(employee);
		companyEmail.setEmailtype(EmailType.CompanyEmailId);
		employee.getEmailids().add(companyEmail);
		
		//set permanent address and temporary address
		
		Address permanentAddress=employee.getPermanentAddress();
		permanentAddress.setEmployee(employee);
		permanentAddress.setAddresstype(AddressType.Permanent);
		employee.getAddresses().add(permanentAddress);
		
		Address temporaryAddress=employee.getTemporaryAddress();
		temporaryAddress.setEmployee(employee);
		temporaryAddress.setAddresstype(AddressType.Temporary);
		employee.getAddresses().add(temporaryAddress);
		
		Organization organization=orgDao.getOrgByname(org.getOrgName());
		
		
		
		//call emailController
		//to send an email to the employee with randomly generated username and password 
		EmployeeUserAccount eua=EmailController.sendEmail(employee.getPersonalEmailID().getEmailAddress(), employee);
		employee.setEmployeeUserAccount(eua);
		
		
		
		
		//orgDao.update(org);
		
		
		employee.setOrg(organization);
		organization.getEmployees().add(employee);
		
		
		Contact contact=new Contact(employee.getFirstName()+"_"+employee.getLastName(),employee.getPersonalEmailID().getEmailAddress());
		contact.setOwnEmployee(employee);
		contact.setOrg(organization);
		organization.getContacts().add(contact);
		employee.setContact(contact);
		contactDao.register(contact);
		
		Employee emp=empDao.register(employee);
		//orgDao.update(org);
		mv.setViewName("index");
		return new ModelAndView("redirect:/admin/homepage");
	}
	
	@RequestMapping(value="/org/addOrgImage",method=RequestMethod.POST)
	public ModelAndView addOrgImageToDB(HttpServletRequest request,@ModelAttribute("orgImage")OrgImage orgImage,BindingResult reuslt) throws IllegalStateException, IOException, OrgImageException, EmployeeException, OrganizationException
	{
		HttpSession session=request.getSession();
		ModelAndView mv=new ModelAndView();
		String filename=orgImage.getOccasion();
		
		if (filename.trim() != "" || filename!= null) {
			File directory;
			String check = File.separator; // Checking if system is linux
											// based or windows based by
											// checking seprator used.
			String path = null;
			if (check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																			  // so we need to replace build in the path
			}

			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				path += "/"; // Adding trailing slash for Mac systems.
			}
			directory = new File(path + "\\"+ filename);
			boolean temp = directory.exists();
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				// We need to transfer to a file
				
				CommonsMultipartFile photoInMemory = orgImage.getFile();

				String fileName = photoInMemory.getOriginalFilename();
				// could generate file names as well

				File localFile = new File(directory.getPath(), fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				orgImage.setFilename(localFile.getPath());
				String filenaame=localFile.getPath();
				String fileString=fileName.substring(fileName.lastIndexOf("/") + 1);
				System.out.println("File string: "+fileString);
				String newFileString=fileString.replaceAll(".jpg", "");
				orgImage.setFileString(newFileString);
				System.out.println("File is stored at" + localFile.getPath());
			
				}			
			 else {
				System.out.println("Failed to create directory!");
			}
		}
		
		OrgImage orgImg = null;
		if(session.getAttribute("org")!=null)
		{
		orgImage.setOrg((Organization)session.getAttribute("org"));
		orgImg=orgImageDao.register(orgImage);
		}
		else{
			if(session.getAttribute("admin")!=null)
			{
				CommonsMultipartFile file=orgImage.getFile();
				try {
					Blob blob = (Blob) Hibernate.getLobCreator((Session) ((LobCreator) getSession()).createBlob(file.getInputStream(), 0));

				
					orgImage.setContent(blob);
					orgImage.setContentType(file.getContentType());
				} catch (IOException e) {
					e.printStackTrace();
				}
				Admin admin=(Admin) session.getAttribute("admin");
				session.setAttribute("org",admin.getOrg());
				Organization organization=orgDao.getOrgByname(((Organization)session.getAttribute("org")).getOrgName());
				orgImage.setOrg(organization);
				organization.getOrgIamges().add(orgImage);
				
				 orgImg=orgImageDao.register(orgImage);
				 orgDao.update(organization);
			}
			
		}
		
		
		mv.setViewName("redirect:/admin/homepage");
		if(orgImg!=null)
		{
			mv.addObject("imageAdded", "SUCCESS");
		}
		else{
			mv.addObject("imageAdded", "FAILURE");
		}
		return mv;
		
	}
	
}

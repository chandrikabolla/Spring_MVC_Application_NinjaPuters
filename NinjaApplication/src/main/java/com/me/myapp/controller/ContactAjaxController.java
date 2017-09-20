package com.me.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.ContactDAO;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.ResultContact;


@RestController
public class ContactAjaxController {
	List<Contact> contacts;
	
	@Autowired
	@Qualifier("contactDao")
	ContactDAO contactDao;
	
	@RequestMapping(value = "/search/api/getSearchResult")
	public ArrayList<ResultContact>  getSearchResultViaAjax(@RequestBody String search) {

		ArrayList<ResultContact> result=new ArrayList<ResultContact>();

		if (isValidSearchCriteria(search)) {
			ArrayList<ResultContact> users = (ArrayList<ResultContact>) findByUserName(search);

			if (users.size() > 0) {
				
				return (ArrayList<ResultContact>) users;
			} else {
				
			}

		} else {
			
		}

		System.out.println("result size: "+result.size());
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	private boolean isValidSearchCriteria(String search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if (StringUtils.isEmpty(search)) {
			valid = false;
		}

		return valid;
	}

	// Init some users for testing
	@PostConstruct
	private void iniDataForTesting() throws RoleException {
		contacts = new ArrayList<Contact>();

		contacts=contactDao.getContacts();
		System.out.println("Contacts size: "+contacts.size());

	}

	// Simulate the search function
	private ArrayList<ResultContact> findByUserName(String username) {

		List<ResultContact> result = new ArrayList<ResultContact>();

		for (Contact contact: contacts) {

			if ((!StringUtils.isEmpty(username))) {

				if (username.equalsIgnoreCase(contact.getContactName())||contact.getContactName().contains(username)) {
					
					ResultContact rc=new ResultContact(contact.getContactName(), contact.getContactEmail());
					result.add(rc);
					continue;
				} else {
					continue;
				}

			}
			

		}

		return (ArrayList<ResultContact>) result;

	}
}

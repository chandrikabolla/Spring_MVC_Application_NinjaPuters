package com.me.myapp.controller;

import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.EmployeeUserAccount;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageBase;

import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;


public class EmailController {
	
	public static EmployeeUserAccount  sendEmail(String receiverEmail,Employee employee){
		 try {
             Email email = new SimpleEmail();
             email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
             email.setSmtpPort(465);
             email.setAuthenticator(new DefaultAuthenticator("chandrikabolla999@gmail.com", "chandrikabolla999"));
             email.setSSLOnConnect(true);
             String senderEmail="chandrikabolla999@gmail.com";
			email.setFrom(senderEmail);//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
             email.setSubject("TestMail");
             UserAccountCreator uaCreator=new UserAccountCreator(employee.getFirstName(),employee.getLastName());
             EmployeeUserAccount eua=uaCreator.createUserAccount();
             eua.setEmployee(employee);
             String msg="This is Ninja Puter Contact Application sending you the email<br/>";
             msg+="<a href='http://localhost:8080/myapp/org/employeeLogin'>Click here to access your account </a><br/>";
             msg+="Your Account Username: ";
             msg+=eua.getUserName();
             msg+="<br/>Your password: ";
             msg+=eua.getPassword();
             email.setContent(msg,"text/html");
             email.addTo(receiverEmail);//Will come from the database
             email.send();
             return eua;
         } catch (Exception e) {
             System.out.println("Email Exception" + e.getMessage());
         }
		return null;
	}
	public static void  sendEmail(String receiverEmail,Admin admin){
		 try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("chandrikabolla999@gmail.com", "chandrikabolla999"));
            email.setSSLOnConnect(true);
            String senderEmail="chandrikabolla999@gmail.com";
			email.setFrom(senderEmail);//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
            email.setSubject("TestMail");
            String msg="This is spring MVC Contact Application sending you the email<br/>";
            msg+="<a href='http://localhost:8080/myapp/org/adminLogin'>Click here to access your account </a><br/>";
            msg+="Your Account Username: ";
            msg+=admin.getAdminUserAccount().getUserName();
            msg+="<br/>Your password: ";
            msg+=admin.getAdminUserAccount().getPassword();
            email.setContent(msg,"text/html");
            email.addTo(receiverEmail);//Will come from the database
            email.send();
           
        } catch (Exception e) {
            System.out.println("Email Exception" + e.getMessage());
        }
	
	}

}

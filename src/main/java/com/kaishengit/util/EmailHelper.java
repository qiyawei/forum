package com.kaishengit.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailHelper {


	
	public static void sendHtmlEmail(String address,String subject,String msg){
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(ConfigProp.get("email.host"));
		mail.setSslSmtpPort(ConfigProp.get("email.port"));
		mail.setAuthentication(ConfigProp.get("email.username"),ConfigProp.get("email.password"));
		mail.setCharset(ConfigProp.get("email.encode"));
		mail.setTLS(true);
		
		try {


			System.out.println(111);
			mail.setFrom(ConfigProp.get("email.from"));
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.addTo(address);
			mail.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}	
		public static void sendTextEmail(String address,String subject,String msg){
			SimpleEmail mail = new SimpleEmail();
			mail.setHostName(ConfigProp.get("email.host"));
			mail.setSmtpPort(Integer.valueOf(ConfigProp.get("email.port")));
			mail.setAuthentication(ConfigProp.get("email.username"),ConfigProp.get("email.password"));
			mail.setCharset(ConfigProp.get("email.encode"));
			mail.setSSL(true);
			mail.setTLS(true);
			
			try {
				mail.setFrom(ConfigProp.get("email.from"));
				mail.setSubject(subject);
				mail.setMsg(msg);
				mail.addTo(address);
				mail.send();
			} catch (EmailException e) {
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

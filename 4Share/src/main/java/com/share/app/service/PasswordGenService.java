package com.share.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.share.app.model.PasswordGen;

@Service
@ApplicationScope
public class PasswordGenService {
	
	private final String path="..\\\\4share\\\\src\\\\main\\\\resources\\password.properties";
	
	public void setPassword(String userType) throws IOException {
    
		PasswordGen pg= new PasswordGen();
		
		pg.setUser(genrator(userType));
	
		
		File file= new File(path);
		if(file.exists()) {
			FileInputStream fis= new FileInputStream(file);
			Properties p= new Properties();
			p.load(fis);
			fis.close();
			FileOutputStream fos= new FileOutputStream(file);
			String type= (userType.equals("USER"))? "USER":"GUEST";
			p.setProperty(type,pg.getUser());
			p.store(fos, null);
			fos.close();
		
		}
	}

	public PasswordGen getPassword() throws IOException {
		 PasswordGen pg= new PasswordGen();
	      
		File file= new File(path);
		if(file.exists()) {
			FileInputStream fis= new FileInputStream(file);
			Properties p= new Properties();
			p.load(fis);
			pg.setUser(p.getProperty("USER"));
			pg.setGuest(p.getProperty("GUEST"));
			fis.close();
		
		
		}else {
			throw new RuntimeException("Password file not Exist..");
		}
		return pg;
	}
	
	private String genrator(String userType) {
		 String user="";
		 user+=userType.charAt(0);
		 char start='a';
		 for(int i=1;i<=2;i++) 
			 user+=(char)((int)(Math.random()*26)+start);
			
		 
		 for(int i=1;i<=3;i++) 
				user+= (int)(Math.random()*10);
		
		 
		 return user;
	}

}

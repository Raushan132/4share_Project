package com.share.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.share.app.constant.Auth_Person;
import com.share.app.constant.SelectFolder;
import com.share.app.repository.FileDetailRepository;
import com.share.app.service.CheckFiles;
import com.share.app.service.PasswordGenService;


@Controller
public class DashboardController {
	
	@Autowired
	private CheckFiles files;
	@Autowired
	private PasswordGenService passwordGenService;
	@Autowired
	private FileDetailRepository fileDetailRespository;

	@RequestMapping(value="/dashboard")
	public String displayDashbard(Model m, Authentication auth) throws IOException {
		files.fileMissing();
		String authorize=auth.getName();
		if(authorize.equalsIgnoreCase(Auth_Person.ADMIN.toString())) {
			m.addAttribute("passwordGen",passwordGenService.getPassword());
		}
		
		if(authorize.equalsIgnoreCase(Auth_Person.GUEST.toString())) {
			String folder=SelectFolder.PUBLIC.toString();
			m.addAttribute("total",fileDetailRespository.countByFolderType(folder));
			m.addAttribute("noOfApp",fileDetailRespository.countByFileSubTypeAndFolderType("application",folder));
			m.addAttribute("noOfImg",fileDetailRespository.countByFileSubTypeAndFolderType("image",folder));
			m.addAttribute("noOfAudio",fileDetailRespository.countByFileSubTypeAndFolderType("audio",folder));
			m.addAttribute("noOfVideo",fileDetailRespository.countByFileSubTypeAndFolderType("video",folder));
			
			
		}else {
		m.addAttribute("total",fileDetailRespository.count());
		m.addAttribute("noOfApp",fileDetailRespository.countByFileSubType("application"));
		m.addAttribute("noOfImg",fileDetailRespository.countByFileSubType("image"));
		m.addAttribute("noOfAudio",fileDetailRespository.countByFileSubType("audio"));
		m.addAttribute("noOfVideo",fileDetailRespository.countByFileSubType("video"));
		
		}
		 
		return "dashboard.html";
	}
}

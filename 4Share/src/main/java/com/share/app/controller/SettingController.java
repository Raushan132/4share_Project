package com.share.app.controller;





import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.app.model.DataPath;
import com.share.app.model.IpConfig;
import com.share.app.service.DataPathService;
import com.share.app.service.PasswordGenService;

@Controller
public class SettingController {
	
	
	
	@Autowired
	private final DataPathService dataPathService;
	
	public SettingController(DataPathService dataPathService) {
		this.dataPathService= dataPathService;
	}
	
	@Autowired 
	private  PasswordGenService passwordGenService;
	

	/**
	 * Display Setting page...
	 * @param m
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/setting")
	public String displaySetting(Model m) throws IOException {
		
		
		
		
		m.addAttribute("location",dataPathService.getDataPath());
		m.addAttribute("passwordGen",passwordGenService.getPassword());
		
		m.addAttribute("hostAddress",new IpConfig());
		return "Setting.html";
	}
	
	/**
	 *  change the location of file of private and public...
	 * @param dp
	 * @return
	 * @throws IOException
	 */
	 @RequestMapping(value = "/changeLoc",method = RequestMethod.POST)
	public String changLocation(@Valid @ModelAttribute("location") DataPath dp) throws IOException {
		  
		 dataPathService.setDataPath(dp);
		 return "redirect:/setting";
	}
	 
	 @RequestMapping(value="/changePassword/{userType}")
	 public String changePassword(@PathVariable String userType) throws IOException {
		 passwordGenService.setPassword(userType);
		 return "redirect:/setting";
	 }
}

package com.share.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.share.app.constant.SelectFolder;
import com.share.app.entity.FileDetail;
import com.share.app.repository.FileDetailRepository;

import java.util.List;

@Controller
public class SearchController {
	
	
	@Autowired
	private FileDetailRepository fileDetailRepository;
	
		
	@RequestMapping(value="/search",method= RequestMethod.GET)
	public String getSearch() {
		return "search.html";
	}
	
	@RequestMapping(value="/search",method= RequestMethod.POST)
	public String getSearchItem(Model m,Authentication auth, @RequestParam String option, String s_keyword) {
		  System.out.println(option+" "+s_keyword);
		  String select=(option.equalsIgnoreCase("type"))?"type":"name";
		  if(s_keyword.isBlank()) return "redirect:/search";
		  List<FileDetail> list=null;
		  if(auth.getName().equalsIgnoreCase("GUEST")) {
			  String folder= SelectFolder.PUBLIC.toString();
			 
			  if(select.equals("type")) 
			       list= fileDetailRepository.findByExtensionLikeAndFolderType(s_keyword, folder);
			  else
				  list= fileDetailRepository.findByFileNameContainingAndFolderType(s_keyword, folder);
		  }else {
			  if(select.equals("type")) 
			       list= fileDetailRepository.findByExtensionLike(s_keyword);
			  else
				  list= fileDetailRepository.findByFileNameContaining(s_keyword);
		  }
		  
		  
		  m.addAttribute("document_data", list);
		   
		return "search.html";
	}
	
	
}

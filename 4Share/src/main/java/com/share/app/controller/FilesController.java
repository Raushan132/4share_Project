package com.share.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.app.constant.Auth_Person;
import com.share.app.constant.SelectFolder;
import com.share.app.entity.FileDetail;
import com.share.app.repository.FileDetailRepository;

@Controller
public class FilesController {

	@Autowired
	FileDetailRepository fileDetailRepository;
	
	
	@RequestMapping(value = "/allFiles", method = RequestMethod.GET)
	public String allFiles(Model m, Authentication authentication) {

		String auth_name = authentication.getName();
		if (auth_name.equals(Auth_Person.GUEST.toString())) {
			List<FileDetail> list = fileDetailRepository.findByFolderType(SelectFolder.PUBLIC.toString());
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);
			
		} else {
			
			Iterable<FileDetail> i = fileDetailRepository.findAll();
			m.addAttribute("count",fileDetailRepository.count());
			m.addAttribute("document_data", i);

		}

		return "document.html";
	}

	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public String documentFiles(Model m, Authentication authentication) {

		String auth_name = authentication.getName();
		if (auth_name.equals(Auth_Person.GUEST.toString())) {
			List<FileDetail> list = fileDetailRepository.findByFileSubTypeAndFolderType("application",SelectFolder.PUBLIC.toString());
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);
		} else {
			List<FileDetail> list = fileDetailRepository.findByFileSubType("application");
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);

		}

		return "document.html";
	}
	
	
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public String imagesFiles(Model m, Authentication authentication) {

		String auth_name = authentication.getName();
		if (auth_name.equals(Auth_Person.GUEST.toString())) {
			List<FileDetail> list = fileDetailRepository.findByFileSubTypeAndFolderType("image",SelectFolder.PUBLIC.toString());
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);
		} else {
			List<FileDetail> list = fileDetailRepository.findByFileSubType("image");
			m.addAttribute("document_data", list);
			m.addAttribute("count",list.size());

		}

		return "document.html";
	}
	
	@RequestMapping(value = "/music", method = RequestMethod.GET)
	public String audioFiles(Model m, Authentication authentication) {

		String auth_name = authentication.getName();
		if (auth_name.equals(Auth_Person.GUEST.toString())) {
			List<FileDetail> list = fileDetailRepository.findByFileSubTypeAndFolderType("audio",SelectFolder.PUBLIC.toString());
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);
		} else {
			List<FileDetail> list = fileDetailRepository.findByFileSubType("audio");
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);

		}

		return "document.html";
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String videoFiles(Model m, Authentication authentication) {

		String auth_name = authentication.getName();
		if (auth_name.equals(Auth_Person.GUEST.toString())) {
			List<FileDetail> list = fileDetailRepository.findByFileSubTypeAndFolderType("video",SelectFolder.PUBLIC.toString());
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);
		} else {
			List<FileDetail> list = fileDetailRepository.findByFileSubType("video");
			m.addAttribute("count",list.size());
			m.addAttribute("document_data", list);

		}

		return "document.html";
	}
	
}

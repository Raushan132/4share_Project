package com.share.app.controller;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.app.entity.FileDetail;
import com.share.app.repository.FileDetailRepository;
import com.share.app.service.PathConfigurService;

@Controller
public class DeleteContoller {
	
	@Autowired
	private FileDetailRepository fileDetailRepository;
	@Autowired
	private PathConfigurService pathConfigureService;

	@RequestMapping(value="/deletefileId/{url}/{input}", method = RequestMethod.GET)
	public String deleteFile(@PathVariable ("url") @NotBlank String url,@PathVariable ("input") @NotBlank String input) {
		
		long id=Long.parseLong(input);
		Optional<FileDetail> fileDetails= fileDetailRepository.findById(id);
		FileDetail fileDetail= fileDetails.get();
		if(pathConfigureService.deleteInDirectory(fileDetail)) {
			
		}
		fileDetailRepository.deleteById(id);
		
		return "redirect:/"+url;
	}
	
	
}

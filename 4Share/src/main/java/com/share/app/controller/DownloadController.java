package com.share.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.app.constant.Auth_Person;
import com.share.app.constant.SelectFolder;
import com.share.app.entity.FileDetail;
import com.share.app.model.DataPath;
import com.share.app.repository.FileDetailRepository;
import com.share.app.service.CheckFiles;
import com.share.app.service.DataPathService;

@Controller
public class DownloadController {

	@Autowired
	DataPathService dataPathService;

	@Autowired
	FileDetailRepository fileDetailRepository;
	@Autowired
	private CheckFiles checkfiles;

	@RequestMapping(value = "/downloadfile/{fileDetail}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable("fileDetail") @NotBlank String input,Authentication authen) throws IOException {
		Long id=1L;
		try {
	     id	= Long.parseLong(input);
		}catch(NumberFormatException e) {
			throw new RuntimeException("Invalid id is entered");
		}
		String authorizedPerson=authen.getName();
		System.out.println(authorizedPerson);
		Optional<FileDetail> fileDetails = fileDetailRepository.findById(id);
		
		FileDetail fileDetail	=fileDetails.get();	
			if(authorizedPerson.equals(Auth_Person.GUEST.toString()) && fileDetail.getFolderType().equals(SelectFolder.PRIVATE.toString()))
				   throw new RuntimeException("Illegal File transfer");
		return getDownloaded(fileDetail);
		
	}

	private ResponseEntity<Resource> getDownloaded(FileDetail fileDetail) throws IOException {

		DataPath dataPath = dataPathService.getDataPath();
		File file = new File(dataPath.getLocation() + File.separator + fileDetail.getFolderType() + File.separator
				+ fileDetail.getFileName());
		 

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileDetail.getFileName());
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		if (!file.exists()) {
			checkfiles.fileMissing();
			throw new RuntimeException("File not Found");
		}

		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}
}

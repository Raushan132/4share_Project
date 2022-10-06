package com.share.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.share.app.constant.SelectFolder;
import com.share.app.entity.FileDetail;
import com.share.app.repository.FileDetailRepository;
import com.share.app.service.CheckFiles;
import com.share.app.service.FileFormat;
import com.share.app.service.PathConfigurService;

@Controller
public class UploadController {

	@Autowired
	private PathConfigurService pathConfigurService;
	@Autowired
	private FileDetailRepository fileDetail;
	
	@Autowired
	private FileFormat fileFormat;
	
	@Autowired
	private CheckFiles files;
	
	private SelectFolder folder;

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.GET)
	public String diplayUploadFiles(@RequestParam ("input") String folder, @RequestParam(value="error", required=false) String error,Model m) {
		this.folder=(folder.equalsIgnoreCase("public"))? SelectFolder.PUBLIC:SelectFolder.PRIVATE;
		m.addAttribute("error",error);
		return "fileUpload.html";
	}
	

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile[] file) {
		
		String folder = this.folder.toString();
		
		for (MultipartFile f : file) {
			FileDetail fd = new FileDetail();
			fd.setFileName(f.getOriginalFilename());
			fd.setFileType(f.getContentType());
			String[] subType= fileFormat.getFileSubTypeAndExtension(f.getContentType(),f.getName());
			fd.setFileSubType(subType[0]);
			fd.setExtension(subType[1]);
			String size= fileFormat.getSizeFormat(f.getSize());
			fd.setSize(size);
			fd.setFileDate(fileFormat.getCurrentDate());
			fd.setFolderType(folder);
			FileDetail fdFromDB =fileDetail.save(fd);

			boolean uploaded = pathConfigurService.uploadToDirectory(f, folder,fdFromDB.getFileId());
			if (!uploaded) {
				fileDetail.deleteById(fdFromDB.getFileId());
				return "redirect:/uploadFiles?input="+folder.toLowerCase()+"&error=true";
			}
			
		}
		return "redirect:/uploadFiles?input="+folder.toLowerCase()+"&error=false";
	}
	
	@RequestMapping(value="/refreshDB/{url}")
	public String getRefreshDataBase(@PathVariable("url") String url) {
		
		files.fileMissing();
		
		return "redirect:/"+url;
	}

}

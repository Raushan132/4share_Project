package com.share.app.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class FileFormat {

	public String getSizeFormat(long size) {
		 double kb= size/1024.0;
		 double mb= size/(1024.0*1024.0);
		 double gb= size/(1024.0*1024.0*1024.0);
			 
		 String str="";
		 if(gb>=1.0) {
			
			 
			 str=String.format("%.2f", gb);
			 str+=" GB";
			 
		 }
		 else if(mb>1.0) {
			
			str=String.format("%.2f",mb)+" MB";
		 }else {
			 str= String.valueOf(kb);
			 str=String.format("%.2f", kb);
			 str+=" KB";
		 }
		 
		 return str;
	}
	
	public String[] getFileSubTypeAndExtension(String fileType, String fileName) {
		 String[] str= fileType.split("[/]");
		   int index= fileName.lastIndexOf(".");
		   String extension= fileName.substring(index+1);
		   str[1]= extension;
		   return str;
		 
	}
	
	public String getCurrentDate() {
		Date d= new Date();
		DateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		return parser.format(d);
	}
	
}

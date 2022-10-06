package com.share.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.share.app.model.DataPath;

@Service
@ApplicationScope
public class DataPathService {

	public boolean setDataPath(DataPath dataPath) throws IOException {
		File f=new File("..\\4share\\src\\main\\resources\\path.loc");
		File test= new File(dataPath.getLocation());
		
		if(test.isDirectory()) {
			
			FileInputStream fis= new FileInputStream(f);
			Properties p= new Properties();
			p.load(fis);
			fis.close();
			FileOutputStream fos= new FileOutputStream(f);
			p.setProperty("path", dataPath.getLocation());
			p.store(fos, null);
			fos.close();
			String path= test.toString();
			
			makeDirectory(path+File.separator+"private");
			makeDirectory(path+File.separator+"public");
			
			
			return true;
		}
		return false;
	}

	public DataPath getDataPath() throws IOException {
		DataPath dataPath= new DataPath();
		File f=new File("..\\4share\\src\\main\\resources\\path.loc");
		Properties p=new Properties();
		FileInputStream fis= new FileInputStream(f);
		p.load(fis);

		dataPath.setLocation(p.getProperty("path"));
		fis.close();
		
		return dataPath;
	}
	
	public boolean makeDirectory(String path) {
		File f = new File(path);
		if (!f.isDirectory())
			f.mkdir();
	
		return true;
	}
}

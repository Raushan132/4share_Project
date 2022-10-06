package com.share.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.share.app.constant.SelectFolder;
import com.share.app.entity.FileDetail;
import com.share.app.model.DataPath;

@Service
public class PathConfigurService {
	private final DataPathService dataPathService;

	@Autowired
	public PathConfigurService(DataPathService dataPathService) {
		this.dataPathService = dataPathService;
	}

	private String getPath() {
		try {
			DataPath dataPath = dataPathService.getDataPath();
			String path = dataPath.getLocation();
			dataPathService.makeDirectory(path + File.separator + "private");
			dataPathService.makeDirectory(path + File.separator + "public");
			return dataPath.getLocation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean uploadToDirectory(MultipartFile file, String folderName, long id) {
		boolean fileUploaded = false;
		String path = getPath();
		if (path != null) {
			path += (folderName.equals(SelectFolder.PRIVATE.toString())) ? File.separator + "private"
					: File.separator + "public";

			File f = new File(path);
			System.out.print(f.isDirectory() + " " + path);
			try {
				Files.copy(file.getInputStream(), Paths.get(path + File.separator + file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				fileUploaded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileUploaded;
	}

	public boolean deleteInDirectory(FileDetail fileDetail) {

		boolean isDeleted = false;
		String path = getPath();
		path += (fileDetail.getFolderType().equals(SelectFolder.PRIVATE.toString())) ? File.separator + "private"
				: File.separator + "public";

		File file = new File(path + File.separator + fileDetail.getFileName());

		if (file.exists() && file.isFile()) {
			isDeleted = file.delete();
		} else {
			throw new RuntimeException("File is not exist..");
		}

		return isDeleted;
	}

}

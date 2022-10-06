package com.share.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share.app.entity.FileDetail;
import com.share.app.model.DataPath;
import com.share.app.repository.FileDetailRepository;

@Service
public class CheckFiles {

	@Autowired
	DataPathService dataPathService;
	@Autowired
	private FileFormat fileFormat;


	private FileDetailRepository fileDetailRepository;

	@Autowired
	public CheckFiles(FileDetailRepository fileDetailRepository) {
		this.fileDetailRepository = fileDetailRepository;
	}

	public void fileMissing() {

		Iterable<FileDetail> list = fileDetailRepository.findAll();
		DataPath dataPath = null;
		String loc = null;
		try {
			dataPath = dataPathService.getDataPath();
			loc = dataPath.getLocation();

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (loc != null) {

			missingFileFromPublic(loc, list);
			missingFileFromPrivate(loc, list);

		}

	}

	private void missingFileFromPublic(String loc, Iterable<FileDetail> list) {

		File f = new File(loc + File.separator + "public");
		HashMap<String, Boolean> map = new HashMap<>();
		if (f.exists()) {
			File[] allFile = f.listFiles();
			for (File file : allFile)
				map.put(file.getName(), true);

		}
		System.out.println(map);
		for (FileDetail fileDetail : list) {
			if (fileDetail.getFolderType().equalsIgnoreCase("public") && !map.containsKey(fileDetail.getFileName())) {
				fileDetailRepository.deleteById(fileDetail.getFileId());
				System.out.println("Deleted form pulic:" + fileDetail.getFileName());

			} else
				map.put(fileDetail.getFileName(), false);

		}

		addMissingFile(f, map, "public");

	}

	private void missingFileFromPrivate(String loc, Iterable<FileDetail> list) {

		File f = new File(loc + File.separator + "private");

		HashMap<String, Boolean> map = new HashMap<>();
		if (f.exists()) {
			File[] allFile = f.listFiles();
			for (File file : allFile)
				map.put(file.getName(), true);

		}

		for (FileDetail fileDetail : list) {
			if (fileDetail.getFolderType().equalsIgnoreCase("private") && !map.containsKey(fileDetail.getFileName()))
				fileDetailRepository.deleteById(fileDetail.getFileId());
			else
				map.put(fileDetail.getFileName(), false);
		}

		addMissingFile(f, map, "private");

	}

	private void addMissingFile(File f, HashMap<String, Boolean> map, String folder) {

		if (!f.exists())
			return;

		File[] allFiles = f.listFiles();
		for (File file : allFiles) {
            if(!file.isFile()) continue;
            
			if (folder.equalsIgnoreCase("public")) {

				if (map.get(file.getName())) {
					FileDetail fileDetail = new FileDetail();
					fileDetail.setFileName(file.getName());
					String type = getContentType(file);
					String[] subType= fileFormat.getFileSubTypeAndExtension(type,file.getName());
					System.out.println(subType);
					fileDetail.setFileType(type);
					fileDetail.setFileSubType(subType[0]);
					fileDetail.setExtension(subType[1]);
					String size= fileFormat.getSizeFormat(file.length());
					fileDetail.setSize(size);
					fileDetail.setFileDate(fileFormat.getCurrentDate());
					fileDetail.setFolderType("PUBLIC");
					fileDetailRepository.save(fileDetail);
				}

			}

			if (folder.equalsIgnoreCase("private")) {

				if (map.get(file.getName())) {
					FileDetail fileDetail = new FileDetail();
					fileDetail.setFileName(file.getName());
					String type = getContentType(file);
					String[] subType= type.split("[/]");
					fileDetail.setFileType(type);
					fileDetail.setFileSubType(subType[0]);
					fileDetail.setExtension(subType[1]);
					String size= fileFormat.getSizeFormat(file.length());
					fileDetail.setSize(size);
					fileDetail.setFileDate(fileFormat.getCurrentDate());
					fileDetail.setFolderType("PRIVATE");
					fileDetailRepository.save(fileDetail);
				}

			}

		}
	}

	private String getContentType(File file) {

		Path path = file.toPath();
		String s = "";
		try {
			s = Files.probeContentType(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;

	}

}

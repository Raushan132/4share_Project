package com.share.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.share.app.entity.FileDetail;

@Repository
public interface FileDetailRepository extends CrudRepository<FileDetail, Long> {

	List<FileDetail> findByFolderType(String folderType);

	List<FileDetail> findByFileSubType(String fileSubType);

	List<FileDetail> findByFileSubTypeAndFolderType(String fileSubType, String folderType);
	
	List<FileDetail> findByFileNameContaining(String fileName);
	
	List<FileDetail> findByFileNameContainingAndFolderType(String fileName, String folderType);
		
	List<FileDetail> findByExtensionLike(String extension);
	
	List<FileDetail> findByExtensionLikeAndFolderType(String extension, String folderType);
	
	long countByFolderType(String folderType);

	long countByFileSubType(String fileSubType);

	long countByFileSubTypeAndFolderType(String fileSubType, String FolderType);
}

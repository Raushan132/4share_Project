package com.share.app.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="files")
public class FileDetail {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
		@GenericGenerator(name="native", strategy="native")
		private long fileId;
		private String size;
		private String fileName;
		private String fileType;
		private String fileSubType;
		private String extension;
		private String fileDate;
		private String folderType;
		
	
}

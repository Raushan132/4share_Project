package com.share.app.model;



import javax.validation.constraints.NotBlank;


import lombok.Data;


@Data
public class DataPath {
	@NotBlank(message="Name must not be blank")
	private String location;
	
	
}

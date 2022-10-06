package com.share.app.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordGen {
	
	@NotBlank(message="not blank allowed")
	private String user;
	@NotBlank(message="not blank allowed")
	private String guest;

}

package com.share.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
         
	
	@ExceptionHandler(Exception.class)
	public ModelAndView getException(Exception exception) {
		ModelAndView model= new ModelAndView();
		model.setViewName("error");
		model.addObject("error", exception.getMessage());
		
		return model;
	}
	
}

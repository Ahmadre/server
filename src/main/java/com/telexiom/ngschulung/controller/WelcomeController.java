package com.telexiom.ngschulung.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@RequestMapping("/")
	public String echo() {
		return "REST-API is up and running (^-^)";
	}

}

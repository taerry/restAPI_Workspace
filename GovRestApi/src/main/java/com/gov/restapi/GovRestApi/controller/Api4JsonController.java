package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class Api4JsonController {

	@GetMapping("/members")
	public String getMember() {
		return "index.html";
	}
	
	@GetMapping("/test")
	public String test() {
		return "home";
	}
}

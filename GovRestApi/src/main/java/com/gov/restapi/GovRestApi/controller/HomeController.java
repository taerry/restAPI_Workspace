package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
    public String home(Model model) {
		model.addAttribute("welcomMsg", "Hello SpringBoot JPA Programming");
		return "home";
	}
	
	@GetMapping("members")
	public String getMember() {
		return "pdataList";
	}

}
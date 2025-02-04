package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SomeTestController {
	
	@GetMapping("/querySelector")
	public String querySelector() {
		return "someTest/querySelector";
	}

}

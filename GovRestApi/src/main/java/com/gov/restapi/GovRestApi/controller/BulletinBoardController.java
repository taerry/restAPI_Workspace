package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BulletinBoardController {
	
	@GetMapping("/bbEditor")
	public String bbEditor() {
		return "toast/bbEditor";
	}

}

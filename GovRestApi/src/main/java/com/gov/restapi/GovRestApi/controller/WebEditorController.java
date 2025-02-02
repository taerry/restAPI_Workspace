package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebEditorController {
	
	@GetMapping("/webEditor")
	public String webEditor() {
		return "toast/webEditor01";
	}

}

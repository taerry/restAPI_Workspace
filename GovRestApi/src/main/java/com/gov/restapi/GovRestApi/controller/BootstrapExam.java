package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootstrapExam {

	@GetMapping("/bootTable")
    public String callbootStrapTable() {
		System.out.println("...Called Bootstrap HTML Table Page...");
		return "bootstrap_table_exam";
	}
	
	@GetMapping("/bootGrid")
    public String callbootStrapGrid() {
		System.out.println("...Called Bootstrap HTML GRID Page...");
		return "bootstrap_grid_exam";
	}
	
}

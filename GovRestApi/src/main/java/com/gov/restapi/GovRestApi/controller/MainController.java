package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping("index2")
    public String index(Model model){
         model.addAttribute("str", "Hello Spring Boot JPA Programming");
        return "index2"; // index2.html -> ${str}
    }
}

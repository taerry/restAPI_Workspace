package com.web.frontendDev.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Value("${spring.application.name}")
    String appName;
	
	// Login form
	  @GetMapping("/login")
	  public String login(Model model) {
	    return "login/login";
	  }

    @GetMapping("/")
    public String homePage(Model model, Principal principal) {
        model.addAttribute("appName", appName);
        
        if (principal == null) {
            model.addAttribute("message", "Welcome to Spring Security, Your need to sign-in.");
        } else {
            model.addAttribute("message", "Hello " + principal.getName());
        }
        
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }
    
    @GetMapping("/movies")
    public String getMovies(Model model) {
    	
    	return "movies/movie_lists";
    }
}

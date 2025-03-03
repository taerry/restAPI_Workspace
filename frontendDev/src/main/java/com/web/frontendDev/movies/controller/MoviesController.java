package com.web.frontendDev.movies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoviesController {

    @GetMapping("/movieLists")
    public String getMovieLists(Model model) {
    	
    	return "movies/movieLists";
    }
}

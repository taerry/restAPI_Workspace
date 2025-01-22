package com.gov.restapi.GovRestApi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gov.restapi.GovRestApi.entity.Book;
import com.gov.restapi.GovRestApi.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@GetMapping("/books")
	public String home(Model model){
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "bookHome"; // homeBook.html
	}
	
	@GetMapping("/about")
	public String about(Model model){
		return "bookAbout";
	}

}

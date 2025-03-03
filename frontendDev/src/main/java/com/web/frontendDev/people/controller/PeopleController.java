package com.web.frontendDev.people.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.frontendDev.people.code.Gender;
import com.web.frontendDev.people.entity.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PeopleController {
	
	@GetMapping("/people")
	String getPeople(Model model) {
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MMMM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		log.info("Today's date and time = "+dateFormat1.format(cal.getTime()));
		System.out.println("Today's date and time = "+dateFormat1.format(cal.getTime()));
		log.info("Today's date and time = "+dateFormat2.format(cal.getTime()));
		System.out.println("Today's date and time = "+dateFormat2.format(cal.getTime()));
		String today = dateFormat2.format(cal.getTime());
		model.addAttribute("today", today);
		
		model.addAttribute("something", "This is comming from the PeopleController");
		
		model.addAttribute("people", Arrays.asList(
				new Person("John", "Smith", Gender.MALE.getType(), 30),
				new Person("Paul", "Smith", Gender.MALE.getType(), 35),
				new Person("Sara", "Hunter", Gender.FEMALE.getType(), 32),
				new Person("Rober", "Taylor", Gender.MALE.getType(), 40),
				new Person("Elizabes", "Taylor", Gender.FEMALE.getType(), 41)
				));
		
		return "people/people";
	}

}

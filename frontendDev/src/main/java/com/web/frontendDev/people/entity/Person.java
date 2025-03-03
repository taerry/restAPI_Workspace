package com.web.frontendDev.people.entity;

import com.web.frontendDev.people.code.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	
}

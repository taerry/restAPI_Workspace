package com.web.frontendDev.people.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
	
	MALE("MALE", "남자"),
	FEMALE("FEMALE", "여자");
	
	private final String type;
	private final String description;
}

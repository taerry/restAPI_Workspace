package com.gov.restapi.GovRestApi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookViewDTO {
	
	private long id;
	private String subject;
	private int price;
	private String author;
	private int page;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}

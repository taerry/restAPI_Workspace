package com.gov.restapi.GovRestApi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	private Long id;
	private int cost;
	private String content;
	private Date createdAt;

}

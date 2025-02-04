package com.gov.restapi.GovRestApi.dto;

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
public class BbReqDto {
	
	private Long customerID;
	private String bbTitle;	
	private String bbCategory;
	private String bbTag;	
	private String bbContent;
	private String bbImagePath;
	private String bbStatus;

}

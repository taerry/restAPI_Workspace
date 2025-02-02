package com.gov.restapi.GovRestApi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private Long id;
    private String username;
    private List<ReviewDTO> reviews;
    
}

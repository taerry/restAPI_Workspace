package com.web.frontendDev.grocery.entity;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Customer {

	private Integer id = null;
    private String name = null;
    private Calendar customerSince = null;
    
}

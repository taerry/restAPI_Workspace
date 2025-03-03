package com.web.frontendDev.grocery.entity;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order {
	
	private Integer id = null;
    private Calendar date = null;
    private Customer customer = null;
    private Set<OrderLine> orderLines = new LinkedHashSet<OrderLine>();
    
    public Order() {
        super();
    }

}

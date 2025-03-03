package com.web.frontendDev.grocery.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderLine {
	
	private Product product = null;
    private Integer amount = null;
    private BigDecimal purchasePrice = null;
    
    public OrderLine() {
        super();
    }

}

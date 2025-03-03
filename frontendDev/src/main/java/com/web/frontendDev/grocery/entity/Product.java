package com.web.frontendDev.grocery.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private Integer id = null;
    private String name = null;
    private BigDecimal price = null;
    private boolean inStock = false;
    private List<Comment> comments = new ArrayList<Comment>();
    
    
    public Product() {
        super();
    }
    
    public Product(final Integer id, final String name, final boolean inStock, final BigDecimal price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }
    
    public boolean isInStock() {
        return this.inStock;
    }
    
}

package com.gov.restapi.GovRestApi.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cost;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();

    // 리뷰(N) : 고객(1)
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false) // FK
    private Customer customer; // PK(id)

    // 리뷰(N) : 책(1)
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false) // FK
    private Book book; // PK(id)
}

package com.gov.restapi.GovRestApi.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String customerName;
    private  int age;
    private String rating;
    private String occupation;
    
    private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist		// Entity가 DB에 Insert 되기 전에 호출됨
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate		// // Entity가 DB에 Update 되기 전에 호출됨
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

    @Column(columnDefinition = "int default  0")
    private int reserves;

    // 고객(1) : 리뷰(N)
    @OneToMany(mappedBy ="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;  //  List<Review> reviews=cus.getReviews()

    // 고객(1) : 장바구니(N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;

}

package com.gov.restapi.GovRestApi.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private int price;
	private String author;
	private int page;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy= "book", cascade= CascadeType.ALL)
	private List<BookImage> bookImages;		//테이블의 컬럼으로 만들면 않됨. 그래서 위에 mappedBy 선언.
	
	@PrePersist		// Entity가 DB에 Insert 되기 전에 호출됨
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate		// // Entity가 DB에 Update 되기 전에 호출됨
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

package com.gov.restapi.GovRestApi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookImage {	// 책(1) 하나에는 여러개의 이미지(N)가 포함된다.

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private int type; // 타입(1-thumb. 2-baisc. 3-detail)
	private String originalFileName;
	private String fileName;
	@ManyToOne
	// book_id : FK
	@JoinColumn(name= "book_id", referencedColumnName = "id", nullable= false)
	private Book book; // id
	
	private LocalDateTime createdAt;
	
	@PrePersist		// Entity가 DB에 Insert 되기 전에 호출됨
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}

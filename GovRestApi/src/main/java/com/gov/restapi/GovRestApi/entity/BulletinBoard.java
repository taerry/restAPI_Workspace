package com.gov.restapi.GovRestApi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulletinBoard {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNO;
	private Long customerID;
	private String boardCategory;
	private String boardStatus;
	private String boardTitle;
	private String boardContent;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int boardSearchCount;
	private int boardLikeCount;
	private int boardScrapCount;
	private String tagName;
	private String boardImagePath;
	
	@PrePersist		// Entity가 DB에 Insert 되기 전에 호출됨
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate		// // Entity가 DB에 Update 되기 전에 호출됨
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	@Builder
    public BulletinBoard(String boardCategory, String boardStatus, String boardTitle, String boardContent, String tagName) {
		this.boardCategory = boardCategory;
		this.boardStatus = boardStatus;
		this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.tagName = tagName;
    }

}

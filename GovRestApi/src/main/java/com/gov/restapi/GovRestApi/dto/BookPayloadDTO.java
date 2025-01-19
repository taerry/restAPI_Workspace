package com.gov.restapi.GovRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookPayloadDTO {

	@NotBlank
	@Schema(description = "책 제목", example = "Java" , requiredMode = RequiredMode.REQUIRED)
	private String subject;
	
	//@NotBlank  // @NotBlank는 문자열에 대해서 유효성 검사를 하는 애노테이션이므로 문자열이 아닌 타입에 해당 애노테이션을 붙이면 데이터 입력 시 오류가 발생함
	private int price;
	
	@NotBlank
	private String author;
	
	//@NotBlank
	private int page;
	
}


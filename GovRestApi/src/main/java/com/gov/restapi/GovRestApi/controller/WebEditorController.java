package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gov.restapi.GovRestApi.dto.BbReqDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebEditorController {
	
	@GetMapping("/webEditor")
	public String webEditor() {
		return "toast/webEditor01";
	}
	
	@ResponseBody
	@PostMapping("/addBbContent")
	public void addBbContent(BbReqDto bbReqDto) {
		
		log.info("..........BB Title : " +bbReqDto.getBbTitle());
		log.info("..........BB Category : " +bbReqDto.getBbCategory());
		log.info("..........BB Tag : " +bbReqDto.getBbTag());
		log.info("..........BB Content : " +bbReqDto.getBbContent());
	}

}

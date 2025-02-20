package com.sping.MakeDev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sping.MakeDev.code.StatusCode;
import com.sping.MakeDev.dto.CreateDeveloper;
import com.sping.MakeDev.dto.DeveloperDto;
import com.sping.MakeDev.service.DMakerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
	
	private final DMakerService dMakerService;
	
	@GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        
        log.info("EMPLOYED StatusCode Value : " + StatusCode.EMPLOYED.getDescription());	//RETIRED
        log.info("RETIRED StatusCode Value : " + StatusCode.RETIRED.getDescription());
        
        return dMakerService.getAllEmployedDevelopers();
        
    }
	
//	@PostMapping("/create-developer")
	@PostMapping("/developer")
//    public CreateDeveloper.Response createDevelopers(
	public ResponseEntity<CreateDeveloper.Response> createDevelopers(
            @Valid @RequestBody final CreateDeveloper.Request request
    ) {
        log.info("request : {}", request);

//        return dMakerService.createDeveloper(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dMakerService.createDeveloper(request));
    }
		
}

package com.web.json.jsonUpload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/json")
@Slf4j
public class JsonFileController {
	
	private static final String FILE_DIRECTORY = "uploads";
	
	// application.properties 에 app.upload.dir을 정의하고, 없는 경우에 default 값으로 user.home (System에 종속적인)
    //@Value("${app.upload.dir:${user.home}}")
    //private String uploadDir;
	
	// JSON 파일 업로드 API
    @PostMapping("/upload")
    public ResponseEntity<String> uploadJsonFile(@RequestParam("file") MultipartFile file) {
    	
    	log.info("Called uploadJsonFile method,");
    	log.info("Upload File Name : " + file);
        try {
            // 업로드 디렉토리 생성
        	// Path를 정의하는 가장 간단한 방법은 Paths.get() 메소드를 호출하는 것
        	// 아래는 작업 폴더(Project 폴더)에서 상대경로를 정의하는 것
            Path directoryPath = Paths.get(FILE_DIRECTORY);
            log.info("Directory Path Name1 : " + directoryPath);
            log.info("Directory Path Name2 : " + directoryPath.toString());
            // Files 객체는 정적 메소드만 있으며, 매개변수로 Path 객체를 사용함
            // Path 객체는 파일이나 디렉토리 경로 정보를 갖고 있는 객체
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 파일 저장
            log.info("Upload File Name : " + file.getOriginalFilename());
            
            Path filePath = directoryPath.resolve(file.getOriginalFilename());
            log.info("File Path Name : " + filePath);
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok("파일 업로드 완료: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일 업로드 실패: " + e.getMessage());
        }
    }
    
 // JSON 파일 다운로드 API
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadJsonFile(@PathVariable String fileName) {
    	
    	log.info("Called downloadJsonFile method,");
    	
        try {
            File file = new File(FILE_DIRECTORY + "/" + fileName);

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = fileInputStream.readAllBytes();
            fileInputStream.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}

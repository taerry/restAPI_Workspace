package com.gov.restapi.GovRestApi.restController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gov.restapi.GovRestApi.dto.BookPayloadDTO;
import com.gov.restapi.GovRestApi.dto.BookViewDTO;
import com.gov.restapi.GovRestApi.entity.Book;
import com.gov.restapi.GovRestApi.entity.BookImage;
import com.gov.restapi.GovRestApi.service.BookImageService;
import com.gov.restapi.GovRestApi.service.BookService;
import com.gov.restapi.GovRestApi.util.ImageUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "API related Book", description = "책을 관리하기 위한 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookRestController {
	
	//@Autowired
	private final BookService bookService;
	private final BookImageService bookImageService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@PostMapping(value="/books", consumes="application/json", produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponse(responseCode = "400", description = "Please add valid name a description")
	@ApiResponse(responseCode = "200", description = "Book added")
//	@Tag(name="BOOK API")
	@Operation(summary="Add an Book", description="책을 추가합니다.")
	// 데이터를 받을때 사용하는 DTO(Payload : 전송되는 순수한 데이터) 
	public ResponseEntity<BookViewDTO> addBook(@Valid @RequestBody BookPayloadDTO bookPayload) {
// @@ControllerAdvice 를 어노테이션한 GlovalExceptionHandler 클래스를 만들어 try ~ catch문을 제거함...
//		try {
//			Book book = new Book();
//			System.out.println(".........Book Subject from bookPayload: " + bookPayload.getSubject());
//			book.setSubject(bookPayload.getSubject());
//			book.setPrice(bookPayload.getPrice());
//			book.setAuthor(bookPayload.getAuthor());
//			book.setPage(bookPayload.getPage());
//			System.out.println(".........Book Subject from book object : " + book.getSubject());
//			book = bookService.save(book);
//			
//			BookViewDTO bookViewDTO = new BookViewDTO(book.getId(), book.getSubject(), book.getPrice(), book.getAuthor(), book.getPage(), book.getCreatedAt(), book.getUpdatedAt());
//			return ResponseEntity.ok(bookViewDTO);	// (200:OK + JSON Data) : ResponseEntity
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
		Book book = new Book();

		book.setSubject(bookPayload.getSubject());
		book.setPrice(bookPayload.getPrice());
		book.setAuthor(bookPayload.getAuthor());
		book.setPage(bookPayload.getPage());
		book = bookService.save(book);
		
		BookViewDTO bookViewDTO = new BookViewDTO(book.getId(), book.getSubject(), book.getPrice(), book.getAuthor(), book.getPage(), book.getCreatedAt(), book.getUpdatedAt());
		return ResponseEntity.ok(bookViewDTO);	// (200:OK + JSON Data) : ResponseEntity
	}
	
	// 전체 목록 보여주기
	@GetMapping(value = "/books", produces = "application/json")
	@ApiResponse(responseCode = "200", description = "List of books")
	@Operation(summary="List book API")
	public List<BookViewDTO> books() {
		List<BookViewDTO> books=new ArrayList<>();
		for(Book book : bookService.getAllBooks()) {
			books.add(new BookViewDTO(book.getId(), book.getSubject(), book.getPrice(), book.getAuthor(), book.getPage(), book.getCreatedAt(), book.getUpdatedAt()));
		} 
		
		return books;// JSON Array :[{ },{ }…]}
	}
	
	// 특정 목록 보여주기
	@GetMapping(value = "/books/{id}", produces = "application/json")
	@ApiResponse(responseCode = "200", description = "“id에 해당하는 책정보를 출력\"")
//	@ApiResponse(responseCode = "400", description = "“id에 해당하는 책이 없습니다.\"")
	@Operation(summary="book id API")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Book> optionalBook=bookService.findById(id);
//		Book book;
//		if(optionalBook.isPresent()) {
//			book=optionalBook.get();
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
		if (optionalBook.isEmpty()){
			throw new NoSuchElementException("Book with id " + id + " not found");
		}
		Book book = optionalBook.get();
		BookViewDTO bookViewDTO = new BookViewDTO(book.getId(), book.getSubject(), book.getPrice(), book.getAuthor(), book.getPage(), book.getCreatedAt(), book.getUpdatedAt());
		return ResponseEntity.ok(bookViewDTO);
	}
	
	// 수정하기
	@PutMapping(value = "/books/{id}", consumes = "application/json",produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)@ApiResponse(responseCode = "400", description = "Please add valid name a description")
	@ApiResponse(responseCode = "204", description = "Book update")
	@Operation(summary = "Update an Book")
	public ResponseEntity<?> update_Book(@Valid @RequestBody BookPayloadDTO bookPayloadDTO,@PathVariable Long id) {
		Optional<Book> optionalBook=bookService.findById(id);
//		Book book;
//		if(optionalBook.isPresent()) {
//			book=optionalBook.get();
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		} 
		if (optionalBook.isEmpty()) {
			throw new NoSuchElementException("Book with id " + id + " not found");
		}
		Book book = optionalBook.get();
		// 수정되는 데이터 교체
		book.setSubject(bookPayloadDTO.getSubject()); 	// 제목 
		book.setPrice(bookPayloadDTO.getPrice());  		// 가격 
		book.setAuthor(bookPayloadDTO.getAuthor()); 	// 저자 
		book.setPage(bookPayloadDTO.getPage()); 
		book=bookService.save(book);
		BookViewDTO bookViewDTO = new BookViewDTO(book.getId(), book.getSubject(), book.getPrice(), book.getAuthor(), book.getPage(), book.getCreatedAt(), book.getUpdatedAt());
		return ResponseEntity.ok(bookViewDTO);
	}
	
	// 삭제하기
	@DeleteMapping(value = "/books/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponse(responseCode = "202", description = "Book deleted")
	@Operation(summary = "Book delete")
	public ResponseEntity<?> delete_Book(@PathVariable Long id) {
		Optional<Book> optionalBook=bookService.findById(id);
//		Book book;
//		if(optionalBook.isPresent()) {
//			book=optionalBook.get();
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
		if (optionalBook.isEmpty()){
			throw new NoSuchElementException("Book with id " + id + " not found");
		}
		Book book = optionalBook.get();
		
		bookService.deleteBook(book);
		ImageUtil.deleteFolder(uploadPath, id); // 데렉토리 삭제
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}
	
	// 파일(Image) 업로드 REST API
	// http://localhost:8090/1/1/upload/
	@PostMapping(value="/{book_id}/{type}/upload", consumes={"multipart/form-data"})
	public ResponseEntity<?> images_upload(@RequestPart(required = true) MultipartFile[] files,
				@PathVariable Long book_id, @PathVariable int type) {
		// book_id 책이 있는지 확인...
		Optional<Book> optionalBook = bookService.findById(book_id);
		if (optionalBook.isEmpty()){
			throw new NoSuchElementException("Book with id " + book_id + " not found");
		}
		Book book = optionalBook.get();
		
		//이미지 저장 결과를 저장하기 위한 변수 선언
		List<String> successImageName = new ArrayList<>();
		List<String> errorImageName = new ArrayList<>();
		
		// MultipartFile[] files 를 받아 새로운 파일명을 랜덤으부여한다.
		int length = 10;
		boolean useLetters = true;
		boolean useNumber = true;
		// files는 배열이라 스트림으로 읽어 하나씩 파일로 처리하며, png, jpg, jpec만 허용
		Arrays.asList(files).stream().forEach(file -> {
			String contentType = file.getContentType();
			log.info(".....File contents type : " + contentType);
			log.info(".....Original File Name : " + file.getOriginalFilename());
			if(contentType.equals("image/png")
					|| contentType.equals("image/jpg")
					|| contentType.equals("image/jpec")) {
				// 정상적인 이미지인 경우 정보를 저장 : List<String>
				successImageName.add(file.getOriginalFilename());
				
				try {
					String fileName = file.getOriginalFilename();
					String generatedString = RandomStringUtils.random(length, useLetters, useNumber);
					log.info(".....Generated String : " + generatedString);
					// 새로운 이미지 이름을 만든다.
					String new_image_name = generatedString + fileName;
					log.info(".....New Image File Name : " + new_image_name);
					if(type == 1) {
						new_image_name = "thumb_" + generatedString + fileName;
					}
					String absolute_fileLocation = ImageUtil.makePath(uploadPath, new_image_name, book_id);
					log.info(".....Absolute File Location : " + absolute_fileLocation);
					System.out.println("File Location : " + absolute_fileLocation);
					Path path = Paths.get(absolute_fileLocation);
					System.out.println("Path : " + path);
					
					if(type != 1) {
						Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					} 
					// 데이터베이스에 이미지 정보(BookImage)를 저장
					BookImage bookImage = new BookImage();
					bookImage.setOriginalFileName(fileName);
					bookImage.setFileName(new_image_name);
					bookImage.setBook(book);	//관계
					bookImage.setType(type);
					bookImageService.save(bookImage);
					if(type == 1) {
						BufferedImage thumbnail = ImageUtil.getThumbnail(file, 300);
						String thumbnail_location = ImageUtil.makePath(uploadPath, new_image_name, book_id);
						//Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
						ImageIO.write(thumbnail, file.getContentType().split("/")[1], new File(thumbnail_location));
					} 
				} catch(Exception e) {
					e.printStackTrace();
					// 이미지 파일이 아닌 경우 정보를 저장 : : List<String>
					errorImageName.add(file.getOriginalFilename());
				}
			} else {
				// 이미지 파일이 아닌 경우 정보를 저장 : : List<String>
				errorImageName.add(file.getOriginalFilename());
			}
		});
		HashMap<String, List<String>> result = new HashMap<>();
		result.put("SUCCESS", successImageName);
		result.put("ERRORS", errorImageName);
		
		List<HashMap<String, List<String>>> response = new ArrayList<>();
		response.add(result);
		
		return ResponseEntity.ok(response);
	}
	
	// 이미지 뷰어 만들기
    @GetMapping("/{image_id}/imageSrc")
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long image_id) throws IOException {
        Optional<BookImage> optionalBook= bookImageService.findById(image_id);
        byte[] imageBytes;
        if(optionalBook.isPresent()){
            BookImage bookImage = optionalBook.get();
            Path imagePath = ImageUtil.getFileAsResource(uploadPath, bookImage.getBook().getId(), bookImage.getFileName());
            imageBytes = Files.readAllBytes(imagePath);

            // Determine the content type based on the file extension
            String fileName = bookImage.getFileName();
            String fileExtension = "";

            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();
            }
  
            MediaType mediaType;
            switch (fileExtension) {
                case "png":
                    mediaType = MediaType.IMAGE_PNG;
                    break;
                case "jpg":
                case "jpeg":
                    mediaType = MediaType.IMAGE_JPEG;
                    break;
                case "gif":
                    mediaType = MediaType.IMAGE_GIF;
                    break;
                default:
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    break;
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
 // 이미지 삭제 하기
    @Operation(summary="Delete Book Image API")
    @DeleteMapping(value = "/{image_id}/delete")
    public ResponseEntity<String> delete_photo(@PathVariable Long image_id) {
        try {
            Optional<BookImage> optionalBookImage = bookImageService.findById(image_id);
            if(optionalBookImage.isPresent()){
                BookImage bookImage = optionalBookImage.get();
                // 디렉토리에서 이미지 삭제
                ImageUtil.deleteImage(uploadPath, bookImage.getBook().getId(), bookImage.getFileName());
                // 테이불에서 삭제
                bookImageService.delete(bookImage);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	@GetMapping("/test")
	@Tag(name="Test API")
	@Operation(summary="Test 함수 실행", description="test() 함수를 실행합니다.")
	public String test() {
		return "Hello, Book User...";
	}
	
}

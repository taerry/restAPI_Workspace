package com.gov.restapi.GovRestApi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gov.restapi.GovRestApi.entity.BookImage;
import com.gov.restapi.GovRestApi.repository.BookImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookImageService {

	private final BookImageRepository bookImageRepository;
	
	public BookImage save(BookImage bookImage) {
		return bookImageRepository.save(bookImage);
	}
	
	public Optional<BookImage> findById(Long image_id) {
        return  bookImageRepository.findById(image_id);
    }

    public void delete(BookImage bookImage) {
         bookImageRepository.delete(bookImage);
    }
}

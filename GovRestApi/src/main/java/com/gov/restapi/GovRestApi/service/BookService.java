package com.gov.restapi.GovRestApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gov.restapi.GovRestApi.entity.Book;
import com.gov.restapi.GovRestApi.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	
	public Book save(Book book){
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Optional<Book> findById(Long id){
		return bookRepository.findById(id);
	}
	
	public void deleteBook(Book book){
		bookRepository.delete(book);
	}
}

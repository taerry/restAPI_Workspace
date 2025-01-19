package com.gov.restapi.GovRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gov.restapi.GovRestApi.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}

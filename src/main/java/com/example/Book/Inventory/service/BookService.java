package com.example.Book.Inventory.service;

import com.example.Book.Inventory.domain.Books;
import com.example.Book.Inventory.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class BookService {


    @Autowired
    BookRepository bookRepository;

    public List<Books> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Books> findBookById(Long id){
        return bookRepository.findBookById(id).stream().toList();
    }

    public List<Books> saveBook(List<Books> book){
        return bookRepository.saveAll(book);
    }

}

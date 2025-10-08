package com.example.Book.Inventory.controller;

import com.example.Book.Inventory.domain.Books;
import com.example.Book.Inventory.repository.BookRepository;
import com.example.Book.Inventory.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
public class BookInventoryController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Books> getAllBook(){
        log.info("getting the books...");
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public List<Books> getBookById(){
        log.info("getting books by ID");
        return bookService.findBookById(2L);
    }

    @PostMapping("/books")
    public ResponseEntity<List<Books>> addBook(@RequestBody List<Books> book){
        log.info("adding books..");
        List<Books> savedbook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedbook);
    }


}

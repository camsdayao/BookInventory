package com.example.Book.Inventory.service;

import com.example.Book.Inventory.domain.Books;
import com.example.Book.Inventory.domain.Orders;
import com.example.Book.Inventory.repository.BookRepository;
import com.example.Book.Inventory.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Orders> placeOrder(List<Orders> order){
        Orders savedOrder = new Orders();
        for(Orders orders : order){
            Books book = bookRepository.findBookById(orders.getBook().getId()).orElseThrow(() -> new RuntimeException());
            if (book.getStock() <= 0) {
                log.info("Insufficient Stock!");
            } else {
                book.setStock(book.getStock() - orders.getQuantity());
                bookRepository.save(book);
                orders.setTimestamp(LocalDateTime.now());
                savedOrder = orderRepository.save(orders);
            }
        }
        return List.of(savedOrder);
    }

}

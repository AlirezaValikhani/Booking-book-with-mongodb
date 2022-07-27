package com.mongoDB.BookingBook.controller;

import com.mongoDB.BookingBook.dto.BookDto;
import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.service.impl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable @RequestParam String id) {
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @PutMapping("/")
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }
}

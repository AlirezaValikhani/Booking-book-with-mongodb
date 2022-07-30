package com.mongoDB.BookingBook.controller;

import com.mongoDB.BookingBook.dto.BookDto;
import com.mongoDB.BookingBook.dto.PaginationDto;
import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.service.impl.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok().body(bookService.delete(id));
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<Book>> search(@PathVariable String term) {
        return ResponseEntity.ok().body(bookService.search(term));
    }

    @PostMapping("/listP")
    public ResponseEntity<List<Book>> paginationSearch(@RequestBody PaginationDto paginationDto) {
        return ResponseEntity.ok().body(bookService.pagination(paginationDto));
    }
}

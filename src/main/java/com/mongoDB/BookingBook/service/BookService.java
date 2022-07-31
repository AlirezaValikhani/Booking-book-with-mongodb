package com.mongoDB.BookingBook.service;

import com.mongoDB.BookingBook.dto.BookDto;
import com.mongoDB.BookingBook.dto.PaginationDto;
import com.mongoDB.BookingBook.dto.SearchPaginationDto;
import com.mongoDB.BookingBook.model.Book;

import java.util.List;

public interface BookService {
    Book save(BookDto bookDto);
    Book findById(String id);
    List<Book> findAll();
    String delete(String id);
    List<Book> search(SearchPaginationDto searchPaginationDto);
}

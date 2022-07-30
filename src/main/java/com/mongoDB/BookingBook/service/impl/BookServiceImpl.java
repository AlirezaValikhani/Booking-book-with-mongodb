package com.mongoDB.BookingBook.service.impl;

import com.mongoDB.BookingBook.dto.BookDto;
import com.mongoDB.BookingBook.dto.PaginationDto;
import com.mongoDB.BookingBook.exception.BookNotFoundException;
import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.repository.BookRepository;
import com.mongoDB.BookingBook.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(BookDto bookDto) {
        Book book = new Book(null,bookDto.getName(), bookDto.getAuthorName(),false,null,null);
        return bookRepository.save(book);
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public String delete(String id) {
        bookRepository.delete(id);
        return "Book deleted successfully";
    }

    @Override
    public List<Book> search(Book book) {
        List<Book> books = bookRepository.search(book);
        if (books.size() == 0)
            throw new BookNotFoundException();
        else return books;
    }

    @Override
    public List<Book> pagination(PaginationDto paginationDto) {
        return bookRepository.pagination(paginationDto.getSize(),paginationDto.getPage());
    }
}

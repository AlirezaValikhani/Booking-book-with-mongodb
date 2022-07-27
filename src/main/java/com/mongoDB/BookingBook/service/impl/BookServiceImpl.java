package com.mongoDB.BookingBook.service.impl;

import com.mongoDB.BookingBook.dto.BookDto;
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
        Book book = new Book(bookDto.getName(), bookDto.getAuthorName(),false);
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

    /*@Override
    public String delete(BookDto bookDto) {
        Book book = findById(bookDto.getId());
        if (book == null)
            return "User not found";
        else bookRepository.delete(book);
        return "Book deleted successfully";
    }*/
}

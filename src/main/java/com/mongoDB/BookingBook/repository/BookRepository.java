package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
}

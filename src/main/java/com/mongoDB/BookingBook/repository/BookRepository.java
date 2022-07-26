package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    @Query("{_id: ?0}")
    Book loadById(String id);
}

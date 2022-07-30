package com.mongoDB.BookingBook.repository;


import com.mongoDB.BookingBook.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryRepository extends MongoRepository<Library,Long> {
}

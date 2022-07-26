package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends MongoRepository<Library,String> {
}

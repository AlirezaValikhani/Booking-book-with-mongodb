package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}

package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{_id: ?0}")
    User loadById(String id);
}

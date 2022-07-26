package com.mongoDB.BookingBook.service;

import com.mongoDB.BookingBook.dto.UserDto;
import com.mongoDB.BookingBook.model.User;

import java.util.List;

public interface UserService {
    User save(UserDto userDto);
    User findById(String id);
    List<User> findAll();
    String delete(UserDto userDto);
}

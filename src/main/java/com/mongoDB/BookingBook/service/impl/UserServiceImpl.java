package com.mongoDB.BookingBook.service.impl;

import com.mongoDB.BookingBook.dto.UserDto;
import com.mongoDB.BookingBook.model.User;
import com.mongoDB.BookingBook.repository.UserRepository;
import com.mongoDB.BookingBook.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getFullName(),userDto.getNationalCode(),userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.loadById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String delete(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user == null)
            return "User not found";
        else userRepository.delete(user);
        return "User deleted successfully";
    }
}

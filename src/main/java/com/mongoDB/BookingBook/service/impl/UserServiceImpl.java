package com.mongoDB.BookingBook.service.impl;

import com.mongoDB.BookingBook.dto.ReserveInfo;
import com.mongoDB.BookingBook.dto.UserDto;
import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.model.User;
import com.mongoDB.BookingBook.repository.BookRepository;
import com.mongoDB.BookingBook.repository.UserRepository;
import com.mongoDB.BookingBook.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;

    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getFullName(),userDto.getNationalCode(),userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String update(UserDto userDto) {
        User user = new User(userDto.getId(),userDto.getFullName(),userDto.getNationalCode(),userDto.getEmail(),null,null);
        userRepository.update(user);
        return "User updated successfully";
    }

    @Override
    public String delete(String id) {
        User user = findById(id);
        if (user == null)
            return "User not found";
        else userRepository.delete(id);
        return "User deleted successfully";
    }


    @Override
    public String reserveBook(ReserveInfo reserveInfo) {
        Book book = bookRepository.findById(reserveInfo.getBookId());
        User user = findById(reserveInfo.getUserId());
        if ( book.getIsReserve().equals(true) )
            return "This book is reserved!";
        else {
            book.setId(reserveInfo.getBookId());
            book.setUser(user);
            book.setIsReserve(true);
            bookRepository.update(book);
            return "Book reserved successfully!";
        }
    }
}

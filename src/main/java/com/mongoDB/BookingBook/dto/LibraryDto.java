package com.mongoDB.BookingBook.dto;

import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibraryDto {
    private String name;
    private List<User> users;
    private List<Book> books;
}

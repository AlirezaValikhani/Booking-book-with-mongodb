package com.mongoDB.BookingBook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Library {
    private String name;
    private List<User> users;
    private List<Book> books;
}

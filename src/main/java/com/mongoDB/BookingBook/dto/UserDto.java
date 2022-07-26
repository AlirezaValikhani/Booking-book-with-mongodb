package com.mongoDB.BookingBook.dto;

import com.mongoDB.BookingBook.model.Book;
import com.mongoDB.BookingBook.model.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String fullName;
    private String nationalCode;
    private String email;
    private List<Book> books;
    private Library library;
}

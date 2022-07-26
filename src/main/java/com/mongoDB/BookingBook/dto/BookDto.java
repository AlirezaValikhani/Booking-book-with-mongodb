package com.mongoDB.BookingBook.dto;

import com.mongoDB.BookingBook.model.Library;
import com.mongoDB.BookingBook.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String id;
    private String name;
    private String authorName;
    private Boolean isReserve;
    private User user;
    private Library library;
}

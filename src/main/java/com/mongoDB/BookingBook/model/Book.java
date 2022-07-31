package com.mongoDB.BookingBook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    //@JsonProperty("_id")
    private String id;
    private String name;
    private String authorName;
    private Boolean isReserve;
    private User user;
    private Library library;

    public Book(String name, String authorName, Boolean isReserve) {
        this.name = name;
        this.authorName = authorName;
        this.isReserve = isReserve;
    }
}

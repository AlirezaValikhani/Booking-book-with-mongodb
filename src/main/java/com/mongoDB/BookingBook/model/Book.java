package com.mongoDB.BookingBook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "book")
public class Book {
    @Id
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

package com.mongoDB.BookingBook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String fullName;
    private String nationalCode;
    private String email;
    private List<Book> books;
    private Library library;

    public User(String fullName, String nationalCode, String email) {
        this.fullName = fullName;
        this.nationalCode = nationalCode;
        this.email = email;
    }
}

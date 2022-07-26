package com.mongoDB.BookingBook.service;

import com.mongoDB.BookingBook.dto.LibraryDto;
import com.mongoDB.BookingBook.model.Library;

import java.util.List;

public interface LibraryService {
    Library save(LibraryDto libraryDto);
    Library findById(String id);
    List<Library> findAll();
    String delete(String id);
}

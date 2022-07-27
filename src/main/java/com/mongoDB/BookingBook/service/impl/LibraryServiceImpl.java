package com.mongoDB.BookingBook.service.impl;

import com.mongoDB.BookingBook.dto.LibraryDto;
import com.mongoDB.BookingBook.model.Library;
import com.mongoDB.BookingBook.repository.LibraryRepository;
import com.mongoDB.BookingBook.service.LibraryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl() {
        this.libraryRepository = null;
    }

    @Override
    public Library save(LibraryDto libraryDto) {
        return null;
    }

    @Override
    public Library findById(String id) {
        return null;
    }

    @Override
    public List<Library> findAll() {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }
}

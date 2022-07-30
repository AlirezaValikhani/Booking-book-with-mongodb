package com.mongoDB.BookingBook.controller;

import com.mongoDB.BookingBook.dto.ReserveInfo;
import com.mongoDB.BookingBook.dto.UserDto;
import com.mongoDB.BookingBook.model.User;
import com.mongoDB.BookingBook.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable @RequestParam String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @RequestParam String id) {
        return ResponseEntity.ok(userService.delete(id));
    }
    @PostMapping("/reserveBook")
    public ResponseEntity<String> reserveBook(@RequestBody ReserveInfo reserveInfo) {
        return ResponseEntity.ok(userService.reserveBook(reserveInfo));
    }
}

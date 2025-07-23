package com.jafar.bookorganizer.controller;

import com.jafar.bookorganizer.entity.Book;
import com.jafar.bookorganizer.entity.User;
import com.jafar.bookorganizer.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userbook")
public class UserBookController {
    @Autowired
    private UserBookService userBookService;

    @PostMapping("/{bookname}/{username}")
    public User addBookToUser(@PathVariable String bookname,@PathVariable String username){
        return userBookService.addBookToUser(username,bookname);
    }

    @GetMapping("/{username}")
    public List<Book> getAllBooksOfAUser(@PathVariable String username){
        return userBookService.getUserBooks(username);
    }

    @DeleteMapping("/{bookname}/{username}")
    public String removeBook(@PathVariable String username,@PathVariable String bookname){
        boolean removed = userBookService.removeBook(username, bookname);
        if (removed){
            return bookname+" is removed from the "+username;
        }return "There is no book present in the "+username;
    }
}

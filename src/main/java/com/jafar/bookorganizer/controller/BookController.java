package com.jafar.bookorganizer.controller;

import com.jafar.bookorganizer.entity.Book;
import com.jafar.bookorganizer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/{bookname}")
    public Book getBookByBookname(@PathVariable String bookname){
        return bookService.getBookByBookname(bookname);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @DeleteMapping("/{bookname}")
    public void deleteBook(@PathVariable String bookname){
        bookService.deleteBook(bookname);
    }
}

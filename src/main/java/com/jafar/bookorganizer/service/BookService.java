package com.jafar.bookorganizer.service;

import com.jafar.bookorganizer.entity.Book;
import com.jafar.bookorganizer.repository.BookRepository;
import com.jafar.bookorganizer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookByBookname(String bookname) {
        Book book = bookRepository.findByBookname(bookname).orElseThrow(() -> new RuntimeException("Book not fouond by name " + bookname));
        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void deleteBook(String bookname) {
        Book book = bookRepository.findByBookname(bookname).orElseThrow(() -> new RuntimeException("Book not fouond by name " + bookname));
        if (!book.isPresent()) {
            userRepository.findAll().forEach(user -> {
                boolean removed = user.getBooks().removeIf(b->b.getId().equals(book.getId()));
            });
        }
        bookRepository.delete(book);
    }
}

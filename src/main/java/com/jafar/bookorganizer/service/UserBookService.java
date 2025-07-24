package com.jafar.bookorganizer.service;

import com.jafar.bookorganizer.entity.Book;
import com.jafar.bookorganizer.entity.User;
import com.jafar.bookorganizer.repository.BookRepository;
import com.jafar.bookorganizer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserBookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public User addBookToUser(String username, String bookname){
        User user = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found with name " + username));
        Book book = bookRepository.findByBookname(bookname).orElseThrow(() -> new RuntimeException("Book not with name " + bookname));
        user.getBooks().add(book);
        book.setPresent(false);
        book.setTakenAt(LocalDateTime.now());
        bookRepository.save(book);
        userRepository.save(user);
        return user;
    }

    public List<Book> getUserBooks(String username){
        User user = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found with name " + username));
        List<Book> books = user.getBooks();
        return books;
    }

    @Transactional
    public boolean removeBook(String username , String bookname){
        boolean removed = false;
        try {
            User user = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found with name " + username));
            Book book = bookRepository.findByBookname(bookname).orElseThrow(() -> new RuntimeException("Book not with name " + bookname));
            removed = user.getBooks().removeIf(b -> b.getId().equals(book.getId()));
            if (removed){
                book.setPresent(true);
                book.setTakenAt(null);
                bookRepository.save(book);
                userRepository.save(user);
            }
        } catch (RuntimeException e) {
            log.error("There is Exception "+e);
            throw  new RuntimeException("An error occured while removing the Book",e);
        }
        return removed;
    }

}

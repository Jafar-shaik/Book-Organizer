package com.jafar.bookorganizer.repository;

import com.jafar.bookorganizer.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,String > {
    Optional<Book> findByBookname(String bookname);
}

package com.jafar.bookorganizer.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    @NonNull
    private String bookname;
    private String title;
    private boolean isPresent =true;
}

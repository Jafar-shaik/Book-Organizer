package com.jafar.bookorganizer.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private List<String> roles = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
}

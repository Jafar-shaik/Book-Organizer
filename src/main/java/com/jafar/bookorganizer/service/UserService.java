package com.jafar.bookorganizer.service;

import com.jafar.bookorganizer.entity.Book;
import com.jafar.bookorganizer.entity.User;
import com.jafar.bookorganizer.repository.BookRepository;
import com.jafar.bookorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BookRepository bookRepository;

    public User addUser(User user){
        List<String > updatedRoles = new ArrayList<>();
        updatedRoles.add("ROLE_USER");
        if (user.getRoles() != null) {
            for (String role : user.getRoles()) {
                String fullRole = "ROLE_"+role.toUpperCase();
                if (!updatedRoles.contains(fullRole)) {
                    updatedRoles.add(fullRole);
                }
            }
        }
        user.setRoles(updatedRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByName(String name){
        User user = userRepository.findByName(name).orElseThrow(()->new RuntimeException("User not found with name "+name));
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(String name , User updatedUser){
        User user = userRepository.findByName(name).orElseThrow(()->new RuntimeException("User not found with name "+name));
        user.setName(updatedUser.getName());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setEmail(updatedUser.getEmail());
        return user;
    }

//    public User updateUserRole(String name,User updatedUser){
//        User user = userRepository.findByName(name).orElseThrow(()->new RuntimeException("User not found with name "+name));
//        List<String > updatedRoles = new ArrayList<>();
//        updatedRoles.add("ROLE_USER");
//        if (!updatedUser.getRoles().isEmpty()) {
//            for (String role : updatedUser.getRoles()) {
//                String fullRole = "ROLE_"+role.toUpperCase();
//                if (!updatedRoles.contains(fullRole)){
//                    updatedRoles.add(fullRole);
//                }
//            }
//        }
//        user.setRoles(updatedRoles);
//        return userRepository.save(user);
//    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(()->new RuntimeException("User not found with name "+name));
        List<Book> books= user.getBooks();
        for (Book book : books) {
            book.setPresent(true);
        }
        userRepository.delete(user);
    }
}

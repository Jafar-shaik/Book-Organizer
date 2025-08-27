package com.jafar.bookorganizer.controller;

import com.jafar.bookorganizer.entity.User;
import com.jafar.bookorganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserConntroller {
    @Autowired
    private UserService userService;

    //Only Admin
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public User getUserByName(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        return userService.getUserByName(username);
    }

    //Only Admin
    @GetMapping("/getall")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @PutMapping("updateuser")
    public User updateUser(@AuthenticationPrincipal UserDetails userDetails,@RequestBody User user){
        String username = userDetails.getUsername();
        return userService.updateUser(username,user);
    }

//    @PutMapping("/{name}")
//    public User updateUserRole(@PathVariable String name,@RequestBody User user){
//        return userService.updateUserRole(name ,user);
//    }

    //Only Admin
    @DeleteMapping("/{name}")
    public void deleteUser(@PathVariable String name){
        userService.deleteUser(name);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public String listOfUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }
    @GetMapping("/users/new")
    public String createUser(Model model){
        model.addAttribute("user",new User());
        return "createUser";
    }
    @PostMapping("/users")
    public String saveUser(@RequestParam("photo")MultipartFile file,@RequestParam("name") String name,@RequestParam("phoneNumber")
                           String phoneNumber,@RequestParam("email") String email) {
        userService.addUser(file,name,phoneNumber,email);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "editUser";
    }
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable int id,@ModelAttribute("user") User user,Model model){
        User user1 = userService.getUserById(id);
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPhoto(user.getPhoto());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setEmail(user.getEmail());
        userService.updateUser(user1);
        return "redirect:/users";
    }

}

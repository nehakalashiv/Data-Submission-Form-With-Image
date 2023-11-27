package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();


    void addUser(MultipartFile file, String name, String phoneNumber, String email) throws IOException;

    User getUserById(int id);
    User updateUser(User user);
}

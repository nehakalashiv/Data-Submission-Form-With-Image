package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(MultipartFile file, String name, String phoneNumber, String email) {
           User user = new User();
           String fileName = StringUtils.cleanPath(file.getOriginalFilename());
           if (fileName.startsWith("..")){
               System.out.println("not available");
           }
           try {


               user.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
           }catch (IOException e){
               e.printStackTrace();
           }
           user.setName(name);
           user.setPhoneNumber(phoneNumber);
           user.getEmail();
           userRepository.save(user);

    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}

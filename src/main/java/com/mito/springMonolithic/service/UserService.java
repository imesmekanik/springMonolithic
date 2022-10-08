package com.mito.loginDeneme.service;

import com.mito.loginDeneme.repository.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mito.loginDeneme.repository.IUserRepository;
import com.mito.loginDeneme.repository.entity.User;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).get();
    }

    public User registerUser(String username, String password, String email, String gender) {
        if (username == null || password == null)
            return null;
        else {
            return userRepository.save(User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .gender(Gender.valueOf(gender))
                    .build());
        }
    }

    public User authanticate(String username,String password){
        return userRepository.findByUsernameAndPassword(username,password).orElse(null);
    }
}

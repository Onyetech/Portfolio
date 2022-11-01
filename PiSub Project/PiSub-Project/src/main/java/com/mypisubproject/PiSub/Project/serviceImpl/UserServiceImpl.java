package com.mypisubproject.PiSub.Project.serviceImpl;

import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public User createUser(User user) {

        user.setPassword(passwordEncode.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {

        return userRepo.existsByEmail(email);
    }

    @Override
    public User authUserLogin(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password).orElse(null);
    }

}

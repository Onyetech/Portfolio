package com.mypisubproject.PiSub.Project.service;

import com.mypisubproject.PiSub.Project.dto.UpdateUserDto;
import com.mypisubproject.PiSub.Project.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

public interface UserService {

    public User createUser(User user);
    public boolean checkEmail(String email);
    public boolean checkUniqueName(String uniqueName);
    public User updateUserDetails(User user, UpdateUserDto updateUser);
    public Optional<User> existsByEmailOrUniqueName(String email, String uniqueName);
    User authUserLogin(String email, String password);

}

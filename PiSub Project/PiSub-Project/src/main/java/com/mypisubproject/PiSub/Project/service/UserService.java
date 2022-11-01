package com.mypisubproject.PiSub.Project.service;

import com.mypisubproject.PiSub.Project.model.User;

public interface UserService {

    public User createUser(User user);
    public boolean checkEmail(String email);

//    User authUserLogin(String email, String password);

}

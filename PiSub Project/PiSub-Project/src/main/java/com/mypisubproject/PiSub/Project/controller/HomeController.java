package com.mypisubproject.PiSub.Project.controller;

import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/pay_status")
    public String payStatus(){
        return "the_call_back_page";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, HttpSession session) {

        System.out.println(user);

        boolean e = userService.checkEmail(user.getEmail());
        boolean n = userService.checkUniqueName(user.getUniqueName());

        if (e || n) {
            session.setAttribute("msg", "Email ID or Unique name already exists");
        }

        else {
            User userDtls = userService.createUser(user);
            if (userDtls != null) {
                session.setAttribute("msg", "Successful, proceed to login");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }
}

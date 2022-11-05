package com.mypisubproject.PiSub.Project.controller;

import com.mypisubproject.PiSub.Project.model.User;

import com.mypisubproject.PiSub.Project.paystack.PaystackPaymentRequest;
import com.mypisubproject.PiSub.Project.paystack.PaystackPaymentResponse;
import com.mypisubproject.PiSub.Project.paystack.PaystackService;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final PaystackService paystackService;
    PaystackPaymentResponse response = new PaystackPaymentResponse();

    public UserController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);

        m.addAttribute("user", user);
    }

    @GetMapping("/my_home")
    public String home() {
        return "/home";
    }

}

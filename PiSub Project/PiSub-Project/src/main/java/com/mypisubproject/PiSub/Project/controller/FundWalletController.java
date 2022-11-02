package com.mypisubproject.PiSub.Project.controller;

import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class FundWalletController {

    @Autowired
    private UserRepository userRepo;


    @ModelAttribute
    private void walletFunding(Model m, Principal p) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);

        m.addAttribute("user", user);
    }

    @GetMapping("/fundwallet")
    public String wallet() {
        System.out.println("I got here at wallet funding");
        return "/fundwallet";
    }


}

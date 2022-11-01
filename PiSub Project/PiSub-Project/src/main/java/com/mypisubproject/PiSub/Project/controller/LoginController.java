//package com.mypisubproject.PiSub.Project.controller;
//
//import com.mypisubproject.PiSub.Project.model.User;
//import com.mypisubproject.PiSub.Project.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
////    @GetMapping("/signin")
////    public String login() {
////        return "login";
////    }
//
//
////    @GetMapping("/login")
////    public String showLoginForm(Model model) {
////
////        model.addAttribute("userLogin", new User());
////        System.out.println("showLoginForm is working now");
////        return "/login";
////    }
//
//    @PostMapping("/user_signin")
//    public String login(@ModelAttribute("userLogin") User user, Model model,
//                        HttpSession session, HttpServletRequest request) {
//
//        session = request.getSession();
//        User authenticatedUser = userService.authUserLogin(user.getEmail(), user.getPassword());
//
//        if (authenticatedUser != null) {
//            session.setAttribute("user", authenticatedUser);
//
//            System.out.println(user);
//
//            model.addAttribute("thisUser", authenticatedUser);
//
//
//            return "dashboard";
//        } else {
//            session.setAttribute("messageFour", "Invalid inputs combination, try again.");
//            return "redirect:/signin";
//        }
//
//    }
//
//}
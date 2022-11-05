package com.mypisubproject.PiSub.Project.controller;

import com.mypisubproject.PiSub.Project.dto.UpdateUserDto;
import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UpdateUserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        System.out.println("Was inside this thing");
        User user = userRepo.findByEmail(email);

        m.addAttribute("user", user);
    }

    @GetMapping("/update")
    public String getUpdateForm(){
        return "updateUser";
    }

    @PostMapping("/updateUserDetails")
    public String updateUser(User user, HttpSession session,
                             @ModelAttribute UpdateUserDto updateUser){

        Optional<User> existsByAny = userService.existsByEmailOrUniqueName(updateUser.getEmail(), updateUser.getUniqueName());
        if (existsByAny.isPresent()){
            session.setAttribute("msg", "Email or Unique name is already in use");
        }
        User userUpdate = userService.updateUserDetails(user, updateUser);
        if (userUpdate != null) {
            session.setAttribute("msg", "Successfully updated your details");
        }
        else {
            session.setAttribute("msg", "Something wrong on server");
        }

        return "redirect:/home";
    }
}

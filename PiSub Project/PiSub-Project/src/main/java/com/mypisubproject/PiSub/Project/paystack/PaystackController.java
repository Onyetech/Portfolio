package com.mypisubproject.PiSub.Project.paystack;

import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
@Api(description = "Paystack pay....", value = "fundWallet")
@RequestMapping("/user")
public class PaystackController {
    private static final Logger logger = LoggerFactory.getLogger(PaystackController.class);
    private final PaystackService paystackService;
    PaystackPaymentResponse response = new PaystackPaymentResponse();

    public PaystackController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

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
        System.out.println("I got here at wallet funding GetMapping");
        return "/fundwallet";
    }

    @PostMapping(value = "/pay_with_paystack")
    public ModelAndView doPayment(@ModelAttribute PaystackPaymentRequest request, Model model) throws Exception {
        logger.info("I am in doPayment controller ......");
        model.addAttribute("payment", request);
        response = paystackService.initTransaction(request);
        if(response.isStatus()) {
            return new ModelAndView("redirect:" + response.getData().getAuthorization_url());
        } else  {
            ModelAndView modelAndView = new ModelAndView("failedInitPage");
            modelAndView.addObject("error-msg", "Dear customer, an error occured while initiating transaction");
            return modelAndView;
        }
    }

    @GetMapping(value = "/callback")
    public String payStackResponse() throws Exception {
        paystackService.verifyTransaction(response.getData().getReference());
        return "/the_call_back_page";
    }

//    @GetMapping(value = "/callback")
//    public ResponseEntity<?> payStackResponse() throws Exception {
//        return new ResponseEntity<>(paystackService.verifyTransaction(response.getData().getReference()), HttpStatus.OK);
//    }

}

package com.mypisubproject.PiSub.Project.paystack;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@Api(description = "Paystack pay....", value = "fundWallet")
@RequestMapping("/pay")
public class PaystackController {

    private static final Logger logger = LoggerFactory.getLogger(PaystackController.class);
    private final PaystackService paystackService;
    PaystackPaymentResponse response = new PaystackPaymentResponse();

    public PaystackController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @PostMapping(value = "/paystack")
    public ResponseEntity<?> doPayment(Principal principal, @RequestBody PaystackPaymentRequest request) throws Exception {
        System.out.println("I am in doPayment controller ..");
        response = paystackService.initTransaction( request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/callback")
    public ResponseEntity<?> payStackResponse() throws Exception {
        return new ResponseEntity<>(paystackService.verifyTransaction(response.getData().getReference()), HttpStatus.OK);
    }

}

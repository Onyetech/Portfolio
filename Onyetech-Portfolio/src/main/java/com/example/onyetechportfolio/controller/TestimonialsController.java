package com.example.onyetechportfolio.controller;

import com.example.onyetechportfolio.dto.TestimonialsRequest;
import com.example.onyetechportfolio.model.Testimonials;
import com.example.onyetechportfolio.service.TestimonialsService;
import com.example.onyetechportfolio.serviceImpl.TestimonialsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("testimonial")
public class TestimonialsController {

    @Autowired
    private TestimonialsService service;

    @PostMapping("/sendTestimonial")
    private String saveMessages(@RequestBody TestimonialsRequest request){
        service.submitTestimonials(request);
        return "Testimonial added successfully! Thank you";
    }


    @GetMapping("/findAll")
    private List<Testimonials> findAllTestimonialMessages(String testimonialMessage){
        return service.findAll(testimonialMessage);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private Page getPaginatedTestimonials(@PathVariable int offset, @PathVariable int pageSize){
        Page<Testimonials> allTestimonialWithPagination = service.findAllTestimonialWithPagination(offset, pageSize);
        return allTestimonialWithPagination;

    }
}

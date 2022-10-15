package com.example.onyetechportfolio.service;

import com.example.onyetechportfolio.dto.TestimonialsRequest;
import com.example.onyetechportfolio.model.Testimonials;
import org.springframework.data.domain.Page;


import java.util.List;

public interface TestimonialsService {


     Testimonials save(Testimonials testimonials);
     String submitTestimonials(TestimonialsRequest request);

     Page<Testimonials> findAllTestimonialWithPagination(int offset, int pageSize);
     List<Testimonials> findAll(String field);




}

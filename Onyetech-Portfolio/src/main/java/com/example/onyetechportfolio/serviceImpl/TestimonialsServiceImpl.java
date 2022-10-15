package com.example.onyetechportfolio.serviceImpl;

import com.example.onyetechportfolio.dto.TestimonialsRequest;
import com.example.onyetechportfolio.model.Testimonials;
import com.example.onyetechportfolio.repository.TestimonialsRepository;
import com.example.onyetechportfolio.service.TestimonialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialsServiceImpl implements TestimonialsService {

    @Autowired
    private TestimonialsRepository testimonialsRepository;

    @Override
    public Testimonials save(Testimonials testimonials) {
        return testimonialsRepository.save(testimonials);
    }

    @Override
    public String submitTestimonials(TestimonialsRequest request) {
        Testimonials testimonials1 = new Testimonials();
        testimonials1.setTestimonial(request.getTestimonial());
        testimonials1.setProfession(request.getProfession());
        testimonials1.setName(request.getName());

        save(testimonials1);

        return "Testimonial is saved. Thank you.";
    }

    @Override
    public Page<Testimonials> findAllTestimonialWithPagination(int offset, int pageSize) {
        Page<Testimonials> allPages = testimonialsRepository.findAll(PageRequest.of(offset, pageSize));
        return allPages;
    }
    @Override
    public List<Testimonials> findAll(String field) {
        return testimonialsRepository.findAll();
    }

}

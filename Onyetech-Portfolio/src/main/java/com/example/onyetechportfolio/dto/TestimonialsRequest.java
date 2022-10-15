package com.example.onyetechportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialsRequest {
    private String name;
    private String profession;
    private String testimonial;
}

package com.example.onyetechportfolio.repository;

import com.example.onyetechportfolio.model.Testimonials;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials,Long> {

    @Query("from testimonials")
    List<Testimonials> findAllById(Pageable pageable);

}

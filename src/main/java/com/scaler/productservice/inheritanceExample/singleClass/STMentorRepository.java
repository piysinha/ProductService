package com.scaler.productservice.inheritanceExample.singleClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface STMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}
package com.scaler.productservice.inheritanceExample.mappedSuperClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MSMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}

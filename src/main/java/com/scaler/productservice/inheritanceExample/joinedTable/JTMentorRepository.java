package com.scaler.productservice.inheritanceExample.joinedTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JTMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}

package com.scaler.productservice.inheritanceExample.tablePerClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "tbc_instructor")
public class Instructor extends User {
    private boolean isInstructor;
}

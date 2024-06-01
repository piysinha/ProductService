package com.scaler.productservice.inheritanceExample.singleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private boolean isInstructor;
}

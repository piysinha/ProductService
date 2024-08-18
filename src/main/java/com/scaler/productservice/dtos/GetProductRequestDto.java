package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductRequestDto {

    private int noOfResult;
    private int offSet;

}

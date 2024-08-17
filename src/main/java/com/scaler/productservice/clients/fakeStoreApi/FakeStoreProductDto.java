package com.scaler.productservice.clients.fakeStoreApi;

import com.scaler.productservice.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements Serializable {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private RatingDto rating;
}

package com.scaler.productservice.clients.fakeStoreApi;

import com.scaler.productservice.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private RatingDto rating;
}

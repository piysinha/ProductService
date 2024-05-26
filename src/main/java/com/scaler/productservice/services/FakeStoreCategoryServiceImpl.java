package com.scaler.productservice.services;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    @Override
    public String getAllCategories() {
        return "";
    }

    @Override
    public String getProductByCategory(Long categoryId) {
        return "";
    }
}

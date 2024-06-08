package com.scaler.productservice.services;

public class SelfCategoryService implements CategoryService{

    private CategoryService categoryService;

    public SelfCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public String getProductByCategory(Long categoryId) {
        return categoryService.getProductByCategory(categoryId);
    }
}

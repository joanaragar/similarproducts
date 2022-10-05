package com.inditex.similarproduct.repository;

import java.util.List;

import com.google.gson.JsonObject;

public class SimilarProductRepository {
    
    private SimilarProductAPI api;

    public SimilarProductRepository(SimilarProductAPI api) {
        this.api = api;
    }

    public JsonObject getProductDetail(String productId) {
        JsonObject productDetailObject = null;
        try {
            productDetailObject = api.getProductDetail(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDetailObject;
    }

    public List<String> getSimilarProductIds(String productId) {
        List<String> idsList = null;
        try {
            idsList = api.getSimilarProductIds(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idsList;
    }

}

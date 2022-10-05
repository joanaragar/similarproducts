package com.inditex.similarproduct.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.inditex.similarproduct.repository.SimilarProductAPI;
import com.inditex.similarproduct.repository.SimilarProductRepository;
import com.inditex.similarproduct.service.SimilarProductService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SimilarProductServiceImpl implements SimilarProductService {

    private static final SimilarProductAPI apiCalls = new SimilarProductAPI();
    private SimilarProductRepository repository = new SimilarProductRepository(apiCalls);
    
    public JsonElement getProductDetail(String productId) {
        JsonObject productDetailObject = repository.getProductDetail(productId);
        if (productDetailObject == null) {
            return null;
        } else {
            if (productDetailObject.toString().contains("Product not found")) {
                return null;
            }
            return productDetailObject;
        }
        
    }

    public List<String> getSimilarProductIds(String productId) {
        List<String> idsList = repository.getSimilarProductIds(productId);
        return idsList;
    }
}

package com.inditex.similarproduct.service;

import java.util.List;

import com.google.gson.JsonElement;

public interface SimilarProductService {

    JsonElement getProductDetail(String productId);

    List<String> getSimilarProductIds(String productId);

}

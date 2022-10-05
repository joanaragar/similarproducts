package com.inditex.similarproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.inditex.similarproduct.service.SimilarProductService;

@RestController
@RequestMapping("/product")
public class SimilarProductController {

    @Autowired
    private final SimilarProductService service;

    public SimilarProductController(SimilarProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<String> getProductSimilar(@PathVariable ("productId") String productId) throws Exception {
                
        if (!service.getSimilarProductIds(productId).toString().contains("[Not Found]")) {

            JsonArray similarProduct = new JsonArray();
            List<String> similarProductIds = service.getSimilarProductIds(productId);
            
            for (int i = 0; i < similarProductIds.size(); i++) {
                JsonElement similarProductElement = service.getProductDetail(similarProductIds.get(i));
                if (similarProductElement != null) {
                    similarProduct.add(similarProductElement);
                }
            }
            
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(similarProduct.toString());

        } else {

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Not Found");
        }
    }
    
}

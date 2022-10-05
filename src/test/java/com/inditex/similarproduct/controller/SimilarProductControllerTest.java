package com.inditex.similarproduct.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inditex.similarproduct.service.SimilarProductService;
import com.inditex.similarproduct.service.impl.SimilarProductServiceImpl;

@SpringBootTest
public class SimilarProductControllerTest {
    
    @Test
    void firstTestGetProductDetailsWithResults() throws Exception {
        SimilarProductService service = new SimilarProductServiceImpl();
        SimilarProductController controller = new SimilarProductController(service);
        ResponseEntity<String> testResponseOne = controller.getProductSimilar("1");

        assertEquals(testResponseOne.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void secondTestGetProductDetailsWithNoResults() throws Exception {
        SimilarProductService service = new SimilarProductServiceImpl();
        SimilarProductController controller = new SimilarProductController(service);
        ResponseEntity<String> testResponseOne = controller.getProductSimilar("1000");

        assertEquals(testResponseOne.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}

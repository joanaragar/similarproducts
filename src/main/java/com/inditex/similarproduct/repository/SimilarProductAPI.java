package com.inditex.similarproduct.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SimilarProductAPI {
    
    public JsonObject getProductDetail(String productId) throws Exception {
        HttpGet httpGet = new HttpGet("http://localhost:3001/product/" + productId);
        return getProductDetailRequest(httpGet);
    }

    public List<String> getSimilarProductIds(String productId) throws Exception {
        HttpGet httpGet = new HttpGet("http://localhost:3001/product/" + productId + "/similarids");
        return getSimilarProductIdsRequest(httpGet);
    }

    public JsonObject getProductDetailRequest(HttpGet getRequest) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getRequest);
        HttpEntity responseEntity = response.getEntity();
        String productDetail = EntityUtils.toString(responseEntity);

        Gson gson = new Gson();
        JsonObject productDetailObject = gson.fromJson(productDetail, JsonObject.class);

        return productDetailObject;
    }

    public List<String> getSimilarProductIdsRequest(HttpGet getRequest) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getRequest);
        HttpEntity responseEntity = response.getEntity();
        String similarProductIds = EntityUtils.toString(responseEntity);

        List<String> idsList = Arrays.asList(similarProductIds.replace("[", "").replace("]", "").split(","));

        return idsList;
    }
}

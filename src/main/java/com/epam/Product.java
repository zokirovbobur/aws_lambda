package com.epam;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

@DynamoDBTable(tableName = "products")
public class Product {
    private String id;
    private String price;
    private String methodType;
    private String productName;
    private String pictureUrl;

    public Product() {
    }

    public Product(String id, String price) {
        this.id = id;
        this.price = price;
    }

    public static Product fromJSON(String json) {
        System.out.println("========json to object transformation block===========");
        Product product = new Product();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            product = objectMapper.readValue(json, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("product after transformation: " + product);
        return product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                       "id='" + id + '\'' +
                       ", price='" + price + '\'' +
                       ", methodType='" + methodType + '\'' +
                       ", name='" + productName + '\'' +
                       ", pictureUrl='" + pictureUrl + '\'' +
                       '}';
    }

    public static Product toProduct(Object input){
        System.out.println("========object transformation block===========");
        LinkedHashMap<String, Object> map = (LinkedHashMap)input;
        System.out.println("map: " + map);
        String body = (String)map.get("body");
        System.out.println("body: " + body);
        Product product = new Product();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            product = objectMapper.readValue(body, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("product after transformation: " + product);
        return product;
    }
}

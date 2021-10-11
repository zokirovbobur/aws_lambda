package com.epam;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoConnector {
    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    private String tableName = "TableNo1";

    public String updateProduct(Product product){
        Table table = dynamoDB.getTable(tableName);
        Item item = new Item().withPrimaryKey("id", "6")
                .withString("price", "16");
        System.out.println("table.putItem(item): " + table.putItem(item));
        return dynamoDB.getTable(tableName).describe().toString();
    }

    public String insertProduct(Product product){
        Table table = dynamoDB.getTable(tableName);
        Item item = new Item().withPrimaryKey("id", product.getId())
                .withString("price", "14");
        System.out.println("table.putItem(item): " + table.putItem(item));
        return dynamoDB.getTable(tableName).describe().toString();
    }
}

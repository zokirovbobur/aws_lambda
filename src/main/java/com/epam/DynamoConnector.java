package com.epam;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class DynamoConnector {
    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    private final Table table = dynamoDB.getTable("products");

    public String createProduct(Product product){
        System.out.println("========createProduct block===========");
        Item item = new Item().withPrimaryKey("id", product.getId())
                .withString("price", product.getPrice());
        System.out.println("table.putItem(item): " + table.putItem(item));
        return "done";
    }

    public String updateProduct(Product product){
        System.out.println("========updateProduct block===========");
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", product.getId(), "price", product.getPrice())
                .withUpdateExpression("set product_name = :n, picture_url = :p")
                .withValueMap(new ValueMap()
                        .withString(":n", product.getProductName())
                        .withString(":p", product.getPictureUrl()))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        System.out.println("table.updateItem(item): " + table.updateItem(updateItemSpec));
        return "done";
    }
}

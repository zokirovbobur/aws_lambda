package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedHashMap;

public class WelcomeLambda implements RequestHandler<Object, Object> {

    private DynamoConnector connector = new DynamoConnector();

    @Override
    public Object handleRequest(Object input, Context context) {
        System.out.println("Hello world");
        return "Hello world";
    }

    public Object insertProduct(Object input, Context context) {
        System.out.println(input);
        System.out.println(context.getAwsRequestId());
        Product product = fromInput(input);
        return connector.insertProduct(product);
    }

    public Object updateProduct(Object input, Context context) {
        System.out.println(input);
        System.out.println(context.getAwsRequestId());
        Product product = fromInput(input);
        return connector.updateProduct(product);
    }

    public Product fromInput(Object input){
        LinkedHashMap<String, String> map = (LinkedHashMap)input;
        System.out.println(map);
        String id = map.get("id");
        Product product = new Product(id,id);
        System.out.println(product);
        return product;
    }
}

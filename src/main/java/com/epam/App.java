package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedHashMap;

public class App implements RequestHandler<Object, Object> {
	private DynamoConnector connector = new DynamoConnector();

	@Override
	public Object handleRequest(Object input, Context context) {
		return staticTest(input, context);
	}

	public Object staticTest(Object input, Context context) {
		System.out.println("input: " + input);
		System.out.println("context.getAwsRequestId(): " + context.getAwsRequestId());
		return connector.staticTest();
	}

	public Object dynamicTest(Object input, Context context) {
		System.out.println("input: " + input);
		System.out.println("context.getAwsRequestId(): " + context.getAwsRequestId());
		Product product = fromInput(input);
		return connector.dynamicTest(product);
	}

	public Product fromInput(Object input){
		LinkedHashMap<String, String> map = (LinkedHashMap)input;
		System.out.println("map: " + map);
		String id = map.get("id");
		String price = map.get("price");
        System.out.println("id: " + id);
		System.out.println("price: " + price);
		Product product = new Product(id,price);
		System.out.println("product: " + product);
		return product;
	}
}

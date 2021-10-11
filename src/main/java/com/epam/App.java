package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		System.out.println("product: " + product);
		return product;
	}
}

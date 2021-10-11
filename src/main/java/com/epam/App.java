package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedHashMap;

public class App implements RequestHandler<Object, Object>
{
	private DynamoConnector connector = new DynamoConnector();

	@Override
	public Object handleRequest(Object input, Context context) {
		System.out.println("Hello world");
		return "Hello world";
	}

	public Object staticTest(Object input, Context context) {
		System.out.println(input);
		System.out.println(context.getAwsRequestId());
		Product product = fromInput(input);
		return connector.staticTest(product);
	}

	public Object dynamicTest(Object input, Context context) {
		System.out.println(input);
		System.out.println(context.getAwsRequestId());
		Product product = fromInput(input);
		return connector.dynamicTest(product);
	}

	public Product fromInput(Object input){
		LinkedHashMap<String, String> map = (LinkedHashMap)input;
		System.out.println(map);
		Long id = Long.valueOf(map.get("id"));
		Product product = new Product(id,String.valueOf(id));
		System.out.println(product);
		return product;
	}
}

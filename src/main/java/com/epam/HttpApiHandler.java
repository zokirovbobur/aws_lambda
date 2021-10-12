package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HttpApiHandler implements RequestHandler<Object, Object> {
	private DynamoConnector connector = new DynamoConnector();

	@Override
	public Object handleRequest(Object input, Context context) {
		System.out.println("input: " + input);
		System.out.println("context.getAwsRequestId(): " + context.getAwsRequestId());
		Product product = Product.toProduct(input);
		if (product.getMethodType().equals("create")){
			return connector.createProduct(product);
		} else if(product.getMethodType().equals("update")){
			return connector.updateProduct(product);
		} else {
			return "methodType is incorrect";
		}
	}
}

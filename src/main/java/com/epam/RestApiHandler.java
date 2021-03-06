package com.epam;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.logging.Logger;

public class RestApiHandler implements RequestStreamHandler {
	private DynamoConnector dynamoConnector = new DynamoConnector();
	static Logger logger = Logger.getLogger(RestApiHandler.class.getName());

	@Override
	public void handleRequest(
		InputStream inputStream, OutputStream outputStream, Context context) {
		JSONObject responseJson = new JSONObject();
		try {
			Product product = requestReader(inputStream);
			logger.info("product entity: " + product);
			String response = null;
			if (product != null){
				logger.info("product is not null");
				if (product.getMethodType().equals("create")){
					response = dynamoConnector.createProduct(product);
					logger.info("product create: " + response);
					responseJson = responseMaker(200, "New item has been created", response);
				} else if(product.getMethodType().equals("update")){
					response = dynamoConnector.updateProduct(product);
					logger.info("product update: " + response);
					responseJson = responseMaker(200, "Item has been updated", response);
				} else {
					logger.info("methodType is incorrect: " + product);
					responseJson = responseMaker(400, "methodType is incorrect", null);
				}
			} else {
				logger.info("body is null");
				responseJson = responseMaker(400, "body is null", null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(outputStream, "UTF-8");
			writer.write(responseJson.toString());
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Product requestReader(InputStream inputStream) throws IOException, ParseException {
		logger.info("requestReader start");
		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		JSONObject event = (JSONObject) parser.parse(reader);
		Object body = event.get("body");
		if (body != null) {
			logger.info("requestReader body reading");
			String stringBody = (String) body;
			logger.fine("stringBody: " + stringBody);
			return Product.fromJSON(stringBody);
		}
		logger.info("requestReader body is empty");
		return null;
	}

	private JSONObject responseMaker(int statusCode, String message, Object data){
		JSONObject responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put("message", message);
		responseBody.put("data", data);

		JSONObject headerJson = new JSONObject();
		headerJson.put("x-custom-header", "my custom header value");

		responseJson.put("statusCode", statusCode);
		responseJson.put("headers", headerJson);
		responseJson.put("body", responseBody.toString());
		return responseJson;
	}
}

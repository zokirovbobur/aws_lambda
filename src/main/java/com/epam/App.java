package com.epam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class App implements RequestHandler<Object, Object>
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

	@Override
	public Object handleRequest(Object o, Context context) {
		return null;
	}
}

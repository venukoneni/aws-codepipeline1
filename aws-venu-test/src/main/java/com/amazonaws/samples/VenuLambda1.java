package com.amazonaws.samples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class VenuLambda1 implements RequestHandler<Object, TestResponse> {

	@Override
	public TestResponse handleRequest(Object input, Context context) {
		TestResponse response=new TestResponse("Venugopal", "Texas"); 
		return response;
	}

	
}

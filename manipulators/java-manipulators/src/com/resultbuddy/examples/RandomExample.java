package com.resultbuddy.examples;
import java.net.UnknownHostException;
import java.util.Random;

import com.resultbuddy.beans.AmbigousResultDescriptorException;
import com.resultbuddy.beans.ResultObject;
import com.resultbuddy.mongomanipulator.MongoManipulator;
import com.resultbuddy.mongomanipulator.MongoManipulatorFactory;


public class RandomExample {

	public static void main(String[] args) throws UnknownHostException, AmbigousResultDescriptorException {
		// Creating manipulator factory, which can handle multiple collections with the same database
		MongoManipulatorFactory mFactory = new MongoManipulatorFactory("localhost", 27017, "local");
		// Creating manipulator for a given collection
		MongoManipulator mManip = mFactory.createManipulator("testCollection");

		// Result object containing the results and the descriptors for the result
		ResultObject result = new ResultObject();
		result.addOrChangeDescriptor("method", "RandomGenerating");

		for (int i = 0; i < 100; i++) {
			result.addOrChangeDescriptor("experiment", i);
			result.addOrChangeResult("output", new Random().nextDouble());
			mManip.updateOrAddResult(result);
		}
	}

}

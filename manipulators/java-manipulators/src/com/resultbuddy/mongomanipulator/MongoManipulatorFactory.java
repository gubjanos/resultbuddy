package com.resultbuddy.mongomanipulator;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


public class MongoManipulatorFactory {
	private DB db;

	public MongoManipulatorFactory(String host, int port, String dbName) throws UnknownHostException {
		ServerAddress address = new ServerAddress(host, port);
		db = new MongoClient(address).getDB(dbName);
	}
	
	public MongoManipulator createManipulator(String collection) {
		return new MongoManipulator(db, collection);
	}
}

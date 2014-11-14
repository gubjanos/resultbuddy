package com.resultbuddy.mongomanipulator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.resultbuddy.beans.AmbigousResultDescriptorException;
import com.resultbuddy.beans.ResultObject;

public class MongoManipulator {
	private DB db;
	private DBCollection collection;
	private String collectionName;
	private boolean isBinded;
	
	public MongoManipulator(DB db, String collection) {
		this.db = db;
		this.collectionName = collection;
		this.isBinded = false;
	}

	public void updateOrAddResult(ResultObject result) throws AmbigousResultDescriptorException {
		if (!isBinded) {
			isBinded = true;
			if (!db.collectionExists(collectionName)) {
				collection = db.createCollection(collectionName, resultToDBObject(result));
				return;
			}

			collection = db.getCollection(collectionName);
		}
		DBObject descriptorObject = resultToDBDescriptor(result);
		DBCursor cursor = collection.find(descriptorObject);
		if (cursor.count() > 1) throw new AmbigousResultDescriptorException("The given result descriptor is ambigous.");

		DBObject actualObject = resultToDBObject(result);
		if (cursor.count() == 0) collection.save(actualObject);
		else {
			actualObject.put("_id", cursor.next().get("_id"));
			collection.save(actualObject);
		}
	}

	public DBObject resultToDBObject(ResultObject result) {
		DBObject dbObject = new BasicDBObject();
		dbObject.putAll(result.getDescriptorMapping());
		DBObject results = new BasicDBObject();
		results.putAll(result.getResultMapping());
		dbObject.put("results", results);
		return dbObject;
	}

	public DBObject resultToDBDescriptor(ResultObject result) {
		DBObject dbObject = new BasicDBObject();
		dbObject.putAll(result.getDescriptorMapping());
		return dbObject;
	}

}

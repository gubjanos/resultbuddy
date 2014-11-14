package com.resultbuddy.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultObject {
	private Map<String, Object> descriptors;
	private Map<String, Object> results;

	public ResultObject() {
		descriptors = new HashMap<>();
		results = new HashMap<>();
	}

	public void addOrChangeDescriptor(String key, Object value) {
		descriptors.put(key, value);
	}

	public void removeDescriptor(String key) {
		descriptors.remove(key);
	}

	public void addOrChangeResult(String key, Object value) {
		results.put(key, value);
	}

	public void removeResult(String key) {
		results.remove(key);
	}

	public Map<String, Object> getDescriptorMapping() {
		return descriptors;
	}

	public Map<String, Object> getResultMapping() {
		return results;
	}
}

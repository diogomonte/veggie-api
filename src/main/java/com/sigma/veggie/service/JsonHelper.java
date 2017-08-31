package com.sigma.veggie.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import spark.ResponseTransformer;

public class JsonHelper {
	
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static <T> T toObject(String json, Class<T> classOfT) throws JsonSyntaxException {
		return new Gson().fromJson(json, classOfT);
	}
	
	public static ResponseTransformer json() {
		return JsonHelper::toJson;
	}

}

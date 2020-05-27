package com.crainyday.sport.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static <E> String List2Json(List<E> list) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	
	public static <E> List<E> Json2List(String json, Class<?> clazz) throws JsonProcessingException{
		if (json == null) {
			json = "[]";
		}
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
		return mapper.readValue(json, javaType);
	}
}

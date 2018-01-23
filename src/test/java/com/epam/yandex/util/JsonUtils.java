package com.epam.yandex.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static <T> T fromJson(File json, Class<T> classOfT) {
		try {
			return mapper.readValue(json, classOfT);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}

package com.epam.yandex.uitests.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper mapper;

	private JsonUtils() {
	}

	public static ObjectMapper getMapper() {
		if (mapper == null) {
			synchronized (JsonUtils.class) {
				if ((mapper == null)) {
					return mapper = new ObjectMapper();
				}
			}
		}
		return mapper;
	}
}

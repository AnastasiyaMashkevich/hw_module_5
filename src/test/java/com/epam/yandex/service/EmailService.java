package com.epam.yandex.service;

import com.epam.yandex.model.Email;
import com.epam.yandex.utils.JsonUtils;
import java.io.IOException;


public class EmailService {

	public static Email getEmail(String data) {
		try {
			return JsonUtils.getMapper().readValue(data, Email.class);
		} catch (IOException e) {
			throw new RuntimeException(" We could not receive a user list.");
		}
	}
}

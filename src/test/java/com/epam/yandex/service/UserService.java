package com.epam.yandex.service;

import com.epam.yandex.model.User;
import com.epam.yandex.model.UserList;
import com.epam.yandex.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserService {

	private static final String USERS_DATA_PATH = "./src/test/resources/users.json";

	private UserList userList;

	public UserService() {
		try {
			userList = JsonUtils.getMapper().readValue(new File(USERS_DATA_PATH), UserList.class);
		} catch (IOException e) {
			throw new RuntimeException(" We could not receive a user list.");
		}
	}

	public List<User> getUserList() {
		return userList.getUser();
	}
}

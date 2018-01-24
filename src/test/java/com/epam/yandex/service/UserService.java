package com.epam.yandex.service;

import com.epam.yandex.model.User;
import com.epam.yandex.model.UserList;
import com.epam.yandex.uitests.utils.JsonUtils;

import java.io.File;
import java.util.List;

public class UserService {

	private static final String USERS_DATA_PATH = "./src/test/resources/users.json";

	private UserList userList;

	public UserService() {
		userList = JsonUtils.fromJson(new File(USERS_DATA_PATH),
				UserList.class);
	}

	public List<User> getUserList() {
		return userList.getUser();
	}
}

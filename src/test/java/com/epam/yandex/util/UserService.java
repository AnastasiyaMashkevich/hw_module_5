package com.epam.yandex.util;

import com.epam.yandex.bean.User;
import com.epam.yandex.bean.UserList;
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

package com.epam.yandex.util;

import com.epam.yandex.bean.UserList;

import java.io.File;


public class UserUtils {

	public static UserList getUserList() {

		return JsonUtils.fromJson(new File("users.json").toString(), UserList.class);
	}
}

package com.epam.yandex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {

	@JsonProperty("users")
	private List<User> user;

	public UserList(){

	}

	@JsonProperty("users")
	public List<User> getUser() {
		return user;
	}

	@JsonProperty("users")
	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserList userList = (UserList) o;
		return user != null ? user.equals(userList.user) : userList.user == null;
	}

	@Override
	public int hashCode() {
		return user != null ? user.hashCode() : 0;
	}
}

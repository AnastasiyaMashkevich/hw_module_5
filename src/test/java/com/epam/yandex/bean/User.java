package com.epam.yandex.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@JsonProperty("name")
	private String name;
	@JsonProperty("login")
	private String login;
	@JsonProperty("psw")
	private String psw;

	public User() {

	}

	@JsonProperty("login")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

	@JsonProperty("psw")
	public String getPsw() {
		return psw;
	}

	@JsonProperty("psw")
	public void setPsw(String psw) {
		this.psw = psw;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (login != null ? !login.equals(user.login) : user.login != null)
			return false;
		return psw != null ? psw.equals(user.psw) : user.psw == null;
	}

	@Override public int hashCode() {
		int result = login != null ? login.hashCode() : 0;
		result = 31 * result + (psw != null ? psw.hashCode() : 0);
		return result;
	}
}


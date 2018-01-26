package com.epam.yandex.uitests.utils.jscommand;


public enum Commands {
	CLICK_ON_ELEMENT("arguments[0].click();"),
	GET_INVISIBLE_ELEMENTS_TEXT("return arguments[0].innerHTML;");

	private String commandString;

	Commands(String commandString) {
		this.commandString = commandString;
	}

	public String getCommandString() {
		return commandString;
	}
}

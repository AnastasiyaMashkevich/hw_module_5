package com.epam.yandex.utils.jscommand;


public enum Commands {
	CLICK_ON_ELEMENT("arguments[0].click();"),
	GET_INVISIBLE_ELEMENTS_TEXT("return arguments[0].innerHTML;"),
	HIGHLIGHT_ELEMENT("arguments[0].style.border='5px solid green'");

	private String commandString;

	Commands(String commandString) {
		this.commandString = commandString;
	}

	public String getCommandString() {
		return commandString;
	}
}

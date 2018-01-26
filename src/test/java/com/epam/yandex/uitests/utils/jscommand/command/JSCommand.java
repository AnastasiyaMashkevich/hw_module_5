package com.epam.yandex.uitests.utils.jscommand.command;

import com.epam.yandex.uitests.utils.jscommand.command.ICommand;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


abstract class JSCommand<T> implements ICommand<T> {
	protected JavascriptExecutor jse;

	protected JSCommand(WebDriver driver) {
		jse = (JavascriptExecutor) driver;
	}

	@Override
	public T execute(Object... args) {
		String command = build();

		return (T) jse.executeScript(command, args);
	}

	//additional method for asynchronous execution
	public T executeAsync(Object... args) {
		String command = build();
		return (T) jse.executeAsyncScript(command, args);
	}

	@Override
	public abstract String build();
}

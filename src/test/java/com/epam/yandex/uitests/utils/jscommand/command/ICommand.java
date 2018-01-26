package com.epam.yandex.uitests.utils.jscommand.command;


public interface ICommand<T>  {

	T execute(Object... args);

	String build();

}

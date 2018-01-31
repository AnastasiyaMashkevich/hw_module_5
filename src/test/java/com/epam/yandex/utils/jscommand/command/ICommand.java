package com.epam.yandex.utils.jscommand.command;


public interface ICommand<T>  {

	T execute(Object... args);

	String build();

}

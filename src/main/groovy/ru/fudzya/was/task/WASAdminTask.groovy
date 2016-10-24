package ru.fudzya.was.task

import ru.fudzya.was.model.ConnType
import ru.fudzya.was.model.Lang

/**
 * Базовый класс описывающий административные инструменты командной строки
 *
 * @author fudzya
 * @since  23.10.2016
 */
abstract class WASAdminTask extends WASTask
{
	/**
	 * Хост WAS
	 */
	String host

	/**
	 * Порт подключения
	 */
	int port

	/**
	 * Тип соединения. По-умолчанию SOAP
	 */
	ConnType connType

	/**
	 * Команда передаваемая обработчику скриптов
	 */
	String command

	/**
	 * Файл *.properties содержащий свойства для JVM
	 */
	String properties

	/**
	 * Файл содержащий скрипт выполняемый до выполнения основного скрипта или команды
	 */
	String profile

	/**
	 * Файл содержащий набор команд передаваемый обработчику скриптов
	 */
	String script

	/**
	 * Язык исполняемого скрипта
	 */
	Lang lang
}

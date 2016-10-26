/*
 * Copyright 2016 Ivan Balovtsev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

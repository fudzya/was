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

/**
 * @author fudzya
 * @since  24.10.2016
 */
abstract class WASServerTask extends WASTask
{
	/**
	 * Имя сервера
	 */
	String server

	/**
	 * Имя скрипта
	 */
	String script

	/**
	 * Время ожидания
	 */
	String timeout

	/**
	 * TCP порт
	 */
	String statusPort

	/**
	 * Имя файла логов для логирования информации о запуске сервера
	 */
	String logFile

	/**
	 * Перезаписывать или нет существующий файл логов
	 */
	boolean replaceLog

	/**
	 * Закончить задачу не дожидаясь запуска сервера
	 */
	boolean noWait

	/**
	 * Печатать или нет информацию о статусе сервера
	 */
	boolean quiet

	/**
	 * Включить или выключить трассировку
	 */
	boolean trace
}

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

package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Описание общих настроек для WAS
 *
 * @author fudzya
 * @since  22.10.2016
 */
class WAS implements Configurable<WAS>
{
	final WASProfile wasProfile = new WASProfile()

	/**
	 * Путь до установленного WAS
	 */
	String wasHome

	/**
	 * Имя пользователя
	 */
	String user

	/**
	 * Пароль пользователя
	 */
	String password

	/**
	 * Хост WAS
	 */
	String host

	/**
	 * Порт подключения
	 */
	String port

	/**
	 * Тип соединения. По-умолчанию SOAP
	 */
	ConnType connType = ConnType.SOAP

	/**
	 * @param closure замыкание используещееся для настройки профиля WAS
	 */
	void wasProfile(Closure closure)
	{
		ConfigureUtil.configure(closure, wasProfile)
	}

	/**
	 *
	 * @param closure замыкание для настройки текущего объекта WAS
	 * @return настроенный текущий экземпляр
	 */
	WAS configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
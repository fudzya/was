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
 * @author fudzya
 * @since  22.10.2016
 */
class WASServer implements Configurable<WASServer>
{
	final WASOptions wasOptions = new WASOptions()

	/**
	 * Имя сервера
	 */
	String serverName

	/**
	 * Ячейка (CELL)
	 */
	String cell

	/**
	 * Узел (NODE)
 	 */
	String node

	/**
	 * @param closure
	 */
	void wasOptions(Closure closure)
	{
		ConfigureUtil.configure(closure, wasOptions)
	}

	/**
	 * @param closure замыкание для настройки сервера
	 * @return настроенный текущий экземпляр
	 */
	WASServer configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
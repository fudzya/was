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

package ru.fudzya.was.task.was

import ru.fudzya.was.WASConstants
import ru.fudzya.was.task.WASServerTask

/**
 * @author fudzya
 * @since 29.10.2016
 */
class WASServerStatusTask extends WASServerTask
{
	/**
	 * Ячейка (CELL)
	 */
	String cell

	/**
	 * Узел (NODE)
	 */
	String node

	/**
	 * Распечатать статус всех серверов
	 */
	boolean all

	@Override
	protected void doExecute(Map<String, ?> arguments)
	{
		super.doExecute(arguments)

		if (!arguments.server)
		{
			throw new IllegalArgumentException('Не задан обязательный параметр server')
		}

		getAnt().taskdef(
			name         : 'serverStatus',
			classname    : WASConstants.CLASS_STATUS_SERVER,
			classpath    : this.getClasspath())

		getAnt().serverStatus(arguments)
	}

	@Override
	protected Map<String, ?> getArguments()
	{
		[
			wasHome      : this.getWasHome(),
			server       : this.getServer(),
			cell         : this.getCell(),
			node         : this.getNode(),
			profileName  : this.getProfileName(),
			timeout      : this.getTimeout(),
			all          : this.isAll(),
			quiet        : this.isQuiet(),
			trace        : this.isTrace(),
			statusPort   : this.getStatusPort(),
			failonerror  : this.isFailOnError(),
			fileEncoding : this.getFileEncoding(),
			debug        : true
		]
	}
}

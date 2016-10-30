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
 * @since  28.10.2016
 */
class WASStopServerTask extends WASServerTask
{
	protected void doExecute(Map<String, ?> arguments = [:])
	{
		super.doExecute(arguments)

		if (!arguments.profileName)
		{
			throw new IllegalArgumentException('Не задан обязательный параметр profileName')
		}

		getAnt().taskdef(
			name         : 'stopServer',
			classname    : WASConstants.CLASS_STOP_SERVER,
			classpath    : this.getClasspath())

		getAnt().stopServer(arguments)
	}


	protected Map<String, ?> getArguments()
	{
		[
			wasHome      : this.getWasHome(),
			server       : this.getServer(),
			profileName  : this.getProfileName(),
			username     : this.getUsername(),
			password     : this.getPassword(),
			timeout      : this.getTimeout(),
			noWait       : this.isNoWait(),
			quiet        : this.isQuiet(),
			trace        : this.isTrace(),
			logFile      : this.getLogFile(),
			replaceLog   : this.isReplaceLog(),
			statusPort   : this.getStatusPort(),
			failonerror  : this.isFailOnError(),
			fileEncoding : this.getFileEncoding(),
			debug        : true
		]
	}
}

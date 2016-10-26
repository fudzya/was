package ru.fudzya.was.task.was

import ru.fudzya.was.WASConstants
import ru.fudzya.was.task.WASServerTask

/**
 * Задача запускающая указанный сервер
 *
 * @author fudzya
 * @since  23.10.2016
 */
class WASStartServerTask extends WASServerTask
{
	protected void doExecute()
	{
		getAnt().taskdef(
			name         : 'startServer',
			classname    : WASConstants.CLASS_START_SERVER,
			classpath    : this.getClasspath())

		getAnt().startServer(
			wasHome      : this.getWasHome(),
			server       : this.getServer(),
			profileName  : this.getProfileName(),
			username     : this.getUsername(),
			password     : this.getPassword(),
			script	     : this.getScript(),
			timeout      : this.getTimeout(),
			noWait       : this.isNoWait(),
			quiet        : this.isQuiet(),
			trace        : this.isTrace(),
			logFile      : this.getLogFile(),
			replaceLog   : this.isReplaceLog(),
			debug        : true,
			statusPort   : this.getStatusPort(),
			failonerror  : this.isFailOnError(),
			fileEncoding : this.getFileEncoding()
		)
	}
}

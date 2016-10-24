package ru.fudzya.was.task

import org.gradle.api.tasks.TaskAction
import ru.fudzya.was.WASConstants

/**
 * Задача запускающая указанный сервер
 *
 * @author fudzya
 * @since 23.10.2016
 */
class WASStartServerTask extends WASServerTask
{
	@TaskAction
	void doExecute()
	{
		getAnt().taskdef(
			name         : 'startServer',
			classname    : WASConstants.CLASS_START_SERVER,
			classpath    : this.getClasspath())

		getAnt().startServer(
			wasHome      : this.getWasHome(),
			server       : this.getServer(),
			username     : this.getUsername(),
			password     : this.getPassword(),
			timeout      : this.getTimeout(),
			noWait       : this.isNoWait(),
			quiet        : this.isQuiet(),
			trace        : this.isTrace(),
			statusPort   : this.getStatusPort(),
			logFile      : this.getLogFile(),
			replaceLog   : this.isReplaceLog(),
			failonerror  : this.isFailOnError(),
			fileEncoding : this.getFileEncoding()
		)
	}
}

package ru.fudzya.was.task

import org.gradle.api.tasks.TaskAction
import ru.fudzya.was.WASConstants

/**
 * Задача запускающая указанный сервер
 *
 * todo realize
 *
 * @author fudzya
 * @since 23.10.2016
 */
class WASStartServerTask extends WASServerTask
{
	WASStartServerTask()
	{
		super()
	}

	@TaskAction
	void doExecute()
	{
		getAnt().taskdef(name      : 'startServer',
						 classname : WASConstants.CLASS_START_SERVER,
						 classpath : this.getClasspath()) {

			wasHome      = this.getWasHome()
			server       = this.getServer()
			profileName  = this.getProfileName()
			noWait       = this.isNoWait()
			quiet        = this.isQuiet()
			logFile      = this.getLogFile()
			replaceLog   = this.isReplaceLog()
			trace        = this.isTrace()
			fileEncoding = this.getFileEncoding()
			username     = this.getUsername()
			password     = this.getPassword()
			failonerror  = this.isFailOnError()
			timeout      = this.getTimeout()
			statusPort   = this.getStatusPort()
		}
	}
}

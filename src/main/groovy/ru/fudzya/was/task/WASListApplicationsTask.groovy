package ru.fudzya.was.task

import org.gradle.api.tasks.TaskAction
import ru.fudzya.was.WASConstants

/**
 * @author fudzya
 * @since  23.10.2016
 */
class WASListApplicationsTask extends WASAdminTask
{
	WASListApplicationsTask()
	{
		super()
	}

	@TaskAction
	void doExecute()
	{
		getAnt().taskdef(name      : 'listApplications',
						 classname : WASConstants.CLASS_LIST_APPS,
						 classpath : this.getClasspath()) {


		}
	}
}

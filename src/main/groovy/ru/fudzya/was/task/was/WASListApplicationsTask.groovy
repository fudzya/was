package ru.fudzya.was.task.was

import ru.fudzya.was.WASConstants
import ru.fudzya.was.task.WASAdminTask

/**
 * @author fudzya
 * @since  23.10.2016
 */
class WASListApplicationsTask extends WASAdminTask
{
	void doExecute(Map<String, ?> arguments = [:])
	{
		super.doExecute(arguments)

		getAnt().taskdef(
			name      : 'listApplications',
			classname : WASConstants.CLASS_LIST_APPS,
			classpath : this.getClasspath())

		getAnt().listApplications(arguments)
	}
}

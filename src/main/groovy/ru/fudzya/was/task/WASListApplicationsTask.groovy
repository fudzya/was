package ru.fudzya.was.task

import com.ibm.websphere.ant.tasks.ListApplications
import org.apache.tools.ant.ProjectHelper
import org.gradle.api.tasks.TaskAction

/**
 * @author fudzya
 * @since  23.10.2016
 */
class WASListApplicationsTask extends WASAdminTask
{
	private final ListApplications listAppTask

	/**
	 *
	 */
	String serverName

	WASListApplicationsTask()
	{
		getAnt().taskdef(
			name      : 'listApplications',
			classname : 'com.ibm.websphere.ant.tasks.ListApplications',
			classpath : getProject().getConfigurations().getByName('was').asPath
		)
	}

	@TaskAction
	void doExecute()
	{
	}
}

package ru.fudzya.was

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.api.internal.NullNamingPropertyException
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.exc.IgnoredPropertyException
import org.gradle.internal.impldep.org.apache.maven.plugin.PluginConfigurationException
import org.gradle.internal.typeconversion.TypeConversionException
import ru.fudzya.was.model.WAS
import ru.fudzya.was.task.WASAdminTask
import ru.fudzya.was.task.WASListApplicationsTask
import ru.fudzya.was.task.WASStartServerTask

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WASPlugin implements Plugin<Project>
{
	void apply(Project project)
	{
		project.getExtensions().create('was', WAS)

		createTasks   (project)
		createConfig  (project)
		configureTasks(project)
	}

	static def createConfig(Project project)
	{
		project.configurations.create('was')
	}

	static def createTasks(Project project)
	{
		project.tasks.create('wasApplications', WASListApplicationsTask)
		project.tasks.create('wasStartServer',  WASStartServerTask)
	}

	static def configureTasks(Project project)
	{
		project.getTasks().withType(WASAdminTask) { WASAdminTask task ->

			final WAS was = project.getExtensions().findByType(WAS)

			task.getConventionMapping().map('wasHome',
			{
				def wasHome = was.getWasHome()
				if (!wasHome)
				{
					wasHome = System.getenv('WAS_HOME')
				}

				if (wasHome)
				{
					def file = new File(wasHome)
					if (file && file.exists())
					{
						return file
					}

					throw new GradleException("Директория $wasHome не существует")
				}

				throw new GradleException('Должна быть установлена переменная окружения WAS_HOME или свойство wasHome')
			})

			task.getConventionMapping().map('user',
			{
				was.getUser()
			})

			task.getConventionMapping().map('password',
			{
				was.getPassword()
			})

			task.getConventionMapping().map('profileName',
			{
				was.getWASProfile().getProfileName()
			})

			task.getConventionMapping().map('host', {
			})

			task.getConventionMapping().map('port', {
			})

			task.getConventionMapping().map('connType', {
			})

			task.getConventionMapping().map('fileEncoding', {
			})

			task.getConventionMapping().map('failOnError', {
			})
		}
	}
}
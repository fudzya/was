package ru.fudzya.was

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.fudzya.was.model.WAS
import ru.fudzya.was.task.WASAdminTask
import ru.fudzya.was.task.WASListApplicationsTask
import ru.fudzya.was.task.WASServerTask
import ru.fudzya.was.task.WASStartServerTask
import ru.fudzya.was.task.WASTask

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WASPlugin implements Plugin<Project>
{
	void apply(Project project)
	{
		project.getExtensions().create(WASConstants.CONFIGURATION_WAS, WAS)

		createTasks   (project)
		createConfig  (project)
		configureTasks(project)
	}

	static def createConfig(Project project)
	{
		project.configurations.create(WASConstants.CONFIGURATION_WAS)
	}

	static def createTasks(Project project)
	{
		project.tasks.create(WASConstants.TASK_LIST_APP,     WASListApplicationsTask)
		project.tasks.create(WASConstants.TASK_START_SERVER, WASStartServerTask)
	}

	static def configureTasks(Project project)
	{
		project.getTasks().withType(WASTask) { WASTask task ->

			final WAS was = project.getExtensions().findByType(WAS)

			task.getConventionMapping().map('wasHome',
			{
				def wasHome = was.getWasHome()
				if (!wasHome)
				{
					project.getLogger().info('Попытка получить директорию установки WebSphere AS из переменной окружения WAS_HOME')
					wasHome = System.getenv('WAS_HOME')
				}

				if (wasHome)
				{
					def file = new File(wasHome)
					if (file && file.exists())
					{
						project.getLogger().info('WebSphere AS установлена в директорию {}', file.path)
						return wasHome
					}

					throw new GradleException("Директория $wasHome не существует")
				}

				throw new GradleException('Должна быть установлена переменная окружения WAS_HOME или свойство wasHome')
			})

			/*
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
			*/
		}

		project.getTasks().withType(WASServerTask) {

		}

		project.getTasks().withType(WASAdminTask) {

		}
	}
}
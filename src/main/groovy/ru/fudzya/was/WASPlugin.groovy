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

package ru.fudzya.was

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.fudzya.was.model.WAS
import ru.fudzya.was.model.WASOptions
import ru.fudzya.was.model.WASProfile
import ru.fudzya.was.model.WASServer
import ru.fudzya.was.task.WASAdminTask
import ru.fudzya.was.task.was.WASListApplicationsTask
import ru.fudzya.was.task.WASServerTask
import ru.fudzya.was.task.was.WASStartServerTask
import ru.fudzya.was.task.WASTask

/**
 * @author fudzya
 * @since  22.10.2016
 */
class WASPlugin implements Plugin<Project>
{
	void apply(Project project)
	{
		project.getExtensions().create(WASConstants.EXTENSION_WAS, WAS)

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
		project.getTasks().with {

			final WAS        wasSrv  = project.getExtensions().findByType(WAS)
			final WASProfile profile = wasSrv.getWasProfile()
			final WASServer  server  = profile.getWasServer()
			final WASOptions options = server.getWasOptions()

			withType(WASTask) { WASTask task ->

				task.getConventionMapping().map('wasHome',
				{
					String wasHome = wasSrv.getWasHome()
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

				task.getConventionMapping().map('username',
				{
					wasSrv.getUser()
				})

				task.getConventionMapping().map('password',
				{
					wasSrv.getPassword()
				})

				task.getConventionMapping().map('profileName',
				{
					profile.getProfileName()
				})

				task.getConventionMapping().map('fileEncoding',
				{
					options.getFileEncoding()
				})
			}

			withType(WASServerTask) { WASServerTask task ->

				task.getConventionMapping().map('server', {
					server.getServerName()
				})

				task.getConventionMapping().map('script', {
					options.getStartServerScript()
				})

				task.getConventionMapping().map('timeout', {
					options.getTimeout()
				})

				task.getConventionMapping().map('statusPort', {
					options.getStatusPort()
				})

				task.getConventionMapping().map('logFile', {
					options.getLogFile()
				})

				task.getConventionMapping().map('replaceLog', {
					options.isReplaceLog()
				})

				task.getConventionMapping().map('noWait', {
					options.isNoWait()
				})

				task.getConventionMapping().map('quiet', {
					options.isQuiet()
				})

				task.getConventionMapping().map('trace', {
					options.isTrace()
				})

				task.getConventionMapping().map('failOnError', {
					options.isFailOnError()
				})
			}

			withType(WASAdminTask) { WASAdminTask task ->

				task.getConventionMapping().map('host', {
					wasSrv.getHost()
				})

				task.getConventionMapping().map('port', {
					wasSrv.getPort()
				})

				task.getConventionMapping().map('connType', {
					wasSrv.getConnType()
				})
			}
		}
	}
}
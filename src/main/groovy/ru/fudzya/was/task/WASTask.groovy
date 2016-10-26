package ru.fudzya.was.task

import org.gradle.api.GradleException
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import ru.fudzya.was.WASConstants

/**
 * Базовый класс описывающий общие для задач поля
 *
 * @author fudzya
 * @since  24.10.2016
 */
abstract class WASTask extends ConventionTask
{
	/**
	 * Путь до установленного WAS
	 */
	String wasHome

	/**
	 * Имя пользователя
	 */
	String username

	/**
	 * Пароль пользователя
	 */
	String password

	/**
	 * Имя профиля
	 */
	String profileName

	/**
	 * Прервать выполнение задачи при ошибке
	 */
	boolean failOnError

	/**
	 * Устанавливает file.encoding для VM
	 */
	String fileEncoding

	/**
	 *  Вычисляет classpath для выполняемого таска следующим образом:
	 *  <br/>
	 *  1. Ищет в папке с установленным WAS папку plugins и записывает в строку путь до каждого jar-файла лежащего в ней;
	 *  <br/>
	 *  2. К получившейся в пункте 1 строке добавляет зависимости определенные в конфигурации {@code WASConstants.CONFIGURATION_WAS}
	 */
	protected String getClasspath()
	{
		def plugins   = new File("${getWasHome()}/plugins")
		def classpath = ''

		if (plugins.exists())
		{
			def jars = plugins.listFiles(new FileFilter() {

				@Override
				boolean accept(File pathname)
				{
					pathname.name.endsWith('.jar')
				}
			})

			jars.each { file ->
				classpath += "${file.path};"
			}
		}

		def cfgClasspath = getProject().getConfigurations().getByName(WASConstants.CONFIGURATION_WAS)?.asPath
		if (cfgClasspath)
		{
			classpath += classpath ? "$classpath$cfgClasspath": cfgClasspath
		}

		if (classpath)
		{
			return classpath
		}

		throw new GradleException('Невозможно вычислить classpath для выполнения задач')
	}

	@TaskAction
	private void exec()
	{
		getLogger().info("Настраиваю 'java.home' на использование java поставляемой с WebSphere AS")
		System.setProperty('java.home', "${this.getWasHome()}\\java")

		getLogger().info("Начинаю исполнение задачи")
		doExecute()
	}

	/**
	 * Каждый таск должен реализовывать данный метод в зависимости от своего назначения
	 */
	protected abstract void doExecute()
}

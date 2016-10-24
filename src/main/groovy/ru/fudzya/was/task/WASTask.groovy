package ru.fudzya.was.task

import org.gradle.api.GradleException
import org.gradle.api.internal.ConventionTask
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
	 * Пароль ползователя
	 */
	String password

	/**
	 * Имя профиля
	 */
	String profileName

	/**
	 * Прервать выполнение задачи при ошибке
	 */
	boolean failOnError = false

	/**
	 * Устанавливает file.encoding для VM
	 */
	String fileEncoding

	WASTask()
	{
		this.fileEncoding = 'UTF-8'
	}

	/**
	 *  Вычисляет classpath для выполняемого таска следующим образом:
	 *  <br/>
	 *  1. Ищет в папке с установленным WAS com.ibm.ws.runtime.jar;
	 *  <br/>
	 *  2. Добавляет к найденному com.ibm.ws.runtime.jar зависимости определенные в конфигурации {@code WASConstants.CONFIGURATION_WAS}
	 */
	protected String getClasspath()
	{
		def wasClasspath = new File("${getWasHome()}/plugins/com.ibm.ws.runtime.jar")
		if (wasClasspath.exists())
		{
			logger.info('Найдена библиотека классов - {}', 'com.ibm.ws.runtime.jar')

			def cfgClasspath = getProject().getConfigurations().getByName(WASConstants.CONFIGURATION_WAS)?.asPath
			if (cfgClasspath)
			{
				logger.info("Конфигурация 'was' содержит следующие библиотеки - {}", cfgClasspath)
				return String.format('%s;%s', wasClasspath.path, cfgClasspath)
			}

			return wasClasspath.path
		}

		throw new GradleException('Невозможно вычислить classpath для выполнения задач')
	}
}

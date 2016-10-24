package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Описание общих настроек для WAS
 *
 * @author fudzya
 * @since  22.10.2016
 */
class WAS implements Configurable<WAS>
{
	final WASProfile wasProfile = new WASProfile()

	/**
	 * Путь до установленного WAS
	 */
	String wasHome

	/**
	 * Имя пользователя
	 */
	String user

	/**
	 * Пароль пользователя
	 */
	String password

	/**
	 * Хост WAS
	 */
	String host

	/**
	 * Порт подключения
	 */
	String port

	/**
	 * Тип соединения. По-умолчанию SOAP
	 */
	ConnType connType = ConnType.SOAP

	/**
	 * @param closure замыкание используещееся для настройки профиля WAS
	 */
	void wasProfile(Closure closure)
	{
		ConfigureUtil.configure(closure, wasProfile)
	}

	/**
	 *
	 * @param closure замыкание для настройки текущего объекта WAS
	 * @return настроенный текущий экземпляр
	 */
	WAS configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
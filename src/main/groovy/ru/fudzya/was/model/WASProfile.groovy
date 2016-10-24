package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WASProfile implements Configurable<WASProfile>
{
	final WASServer wasServer = new WASServer()

	/**
	 * Имя профиля WAS
	 */
	String profileName

	/**
	 * @param closure замыкание для настройки сервера
	 */
	void wasServer(Closure closure)
	{
		ConfigureUtil.configure(closure, wasServer)
	}

	/**
	 * @param closure замыкание для настройки профиля
	 * @return настроенный текущий экземпляр
	 */
	WASProfile configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
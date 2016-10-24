package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since  22.10.2016
 */
class WASServer implements Configurable<WASServer>
{
	final WASOptions wasOptions = new WASOptions()

	/**
	 * Имя сервера
	 */
	String serverName

	/**
	 * @param closure
	 */
	void wasOptions(Closure closure)
	{
		ConfigureUtil.configure(closure, wasOptions)
	}

	/**
	 * @param closure замыкание для настройки сервера
	 * @return настроенный текущий экземпляр
	 */
	WASServer configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
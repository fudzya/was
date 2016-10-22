package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WASProfile implements Configurable<WASProfile>
{
	private WASServer wasServer

	String profileName

	void wasServer(Closure closure)
	{
		wasServer = ConfigureUtil.configure(closure, new WASServer())
	}

	WASServer getWASServer()
	{
		wasServer
	}

	WASProfile configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WAS implements Configurable<WAS>
{
	String wasHome
	String user
	String password

	private WASProfile wasProfile

	void wasProfile(Closure closure)
	{
		wasProfile = ConfigureUtil.configure(closure, new WASProfile())
	}

	WASProfile getWASProfile()
	{
		wasProfile
	}

	WAS configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
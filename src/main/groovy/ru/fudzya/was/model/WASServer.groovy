package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since 22.10.2016
 */
class WASServer implements Configurable<WASServer>
{
	String name
	String userName
	String password

	WASServer configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * todo javadoc
 *
 * @author fudzya
 * @since  22.10.2016
 */
class WASServer implements Configurable<WASServer>
{
	/**
	 *
	 */
	String serverName

	WASServer configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}
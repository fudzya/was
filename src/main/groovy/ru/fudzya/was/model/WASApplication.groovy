package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since  22.10.2016
 */
class WASApplication implements Configurable<WASApplication>
{
	WASApplication configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}

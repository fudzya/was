package ru.fudzya.test

import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author fudzya
 * @since  22.10.2016
 */
class DefaultDefaultWASServerConfigurationTest extends WASTest
{
	@Before
	public void setUp() throws Exception
	{
		configureBuildFile()
	}

	@Test
	void testWASStart() throws Exception
	{
		when:
		{
			def result = GradleRunner.create()
									 .withProjectDir(buildDir.root)
									 .withArguments('wasStartServer', '--info')
									 .withPluginClasspath(getClassPath())
									 .withDebug(true)
									 .build()
		}

		then:
		{
			println result.task(':wasStartServer').outcome
		}
	}
}

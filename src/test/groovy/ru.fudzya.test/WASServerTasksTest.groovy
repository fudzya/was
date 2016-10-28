package ru.fudzya.test

import org.gradle.testkit.runner.GradleRunner
import org.junit.After
import org.junit.Test

/**
 * @author fudzya
 * @since  22.10.2016
 */
class WASServerTasksTest extends WASTest
{
	@Test
	void 'Start WebSphere AS'() throws Exception
	{
		def result

		given:
		{
			configureBuildFile('startServer.gradle')
		}

		when:
		{
			result = GradleRunner.create()
								 .withProjectDir(buildDir.root)
								 .withArguments('wasStartServer', '--info')
								 .withPluginClasspath(getClassPath())
								 .withDebug(true)
								 .build()
		}

		then:
		{
			assert result.task(':wasStartServer').getOutcome().name() == 'SUCCESS'
		}
	}

	@Test
	void 'Stop WebSphere AS'() throws Exception
	{
		def result

		given:
		{
			configureBuildFile('stopServer.gradle')
		}

		when:
		{
			result = GradleRunner.create()
					.withProjectDir(buildDir.root)
					.withArguments('wasStopServer', '--info')
					.withPluginClasspath(getClassPath())
					.withDebug(true)
					.build()
		}

		then:
		{
			assert result.task(':wasStopServer').getOutcome().name() == 'SUCCESS'
		}
	}

	@After
	public void tearDown() throws Exception
	{
		cleanBuildFile()
	}
}

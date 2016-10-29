package ru.fudzya.test

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * @author fudzya
 * @since  22.10.2016
 */
class WASServerTasksTest extends WASTest
{
	@BeforeClass
	void beforeClass() throws Exception
	{
		super.beforeClass()
	}

	@Test
	void 'Start WebSphere AS'() throws Exception
	{
		testTaskWithName 'wasStartServer'
	}

	@Test(dependsOnMethods = 'Start WebSphere AS', alwaysRun = true)
	void 'WebSphere AS Status'() throws Exception
	{
	}

	@Test(dependsOnMethods = 'WebSphere AS Status')
	void 'Stop WebSphere AS'() throws Exception
	{
		testTaskWithName 'wasStopServer'
	}

	private testTaskWithName(String taskName)
	{
		def result

		given:
		{
			configureBuildFile("${taskName}.gradle")
		}

		when:
		{
			result = GradleRunner.create()
					.withProjectDir(buildDir)
					.withArguments(taskName, '--info')
					.withPluginClasspath(getClassPath())
					.withDebug(true)
					.build()
		}

		then:
		{
			assert result.task(":$taskName").getOutcome().name() == 'SUCCESS'
		}
	}

	@AfterMethod
	void methodDown() throws Exception
	{
		super.methodDown()
	}

	@AfterClass
	void classDown() throws Exception
	{
		super.classDown()
	}
}

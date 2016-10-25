package ru.fudzya.test

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.Test
import ru.fudzya.was.WASConstants

import static org.junit.Assert.*

/**
 * @author fudzya
 * @since 22.10.2016
 */
class DefaultDefaultWASServerConfigurationTest
{
	private File testBuildDir

	@Before
	public void setUp() throws Exception
	{
		def buildDir = System.getProperty('testBuildDir')
		if (buildDir == null)
		{
			// TODO delete it
			testBuildDir = new File('E:\\projects\\was\\src\\test\\wasPluginTest')
		}
		else
		{
			testBuildDir = new File(buildDir)
		}
	}

	@Test
	public void testWASConfigure() throws Exception
	{
		try
		{
			BuildResult buildResult = GradleRunner.create()
												  .withProjectDir(testBuildDir)
												  .withArguments(WASConstants.TASK_START_SERVER)
												  .build()

			println buildResult.output
		}
		catch (Exception e)
		{
			e.printStackTrace()
		}
	}
}

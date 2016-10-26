package ru.fudzya.test

import org.junit.Rule
import org.junit.rules.TemporaryFolder

/**
 * @author fudzya
 * @since  26.10.2016
 */
abstract class WASTest
{
	@Rule
	public TemporaryFolder buildDir = [:]

	protected File  buildFile
	protected final List<File> classpath = []

	protected File configureBuildFile()
	{
		if (!buildFile)
		{
			def testBuild = getClass().getClassLoader().getResource('build.gradle')
			if (!testBuild)
			{
				throw new FileNotFoundException('Не найден класс build.gradle')
			}

			buildFile = buildDir.newFile('build.gradle')
			new File(testBuild.path).withReader { reader ->
				reader.eachLine { line ->
					buildFile << "\n$line"
				}
			}
		}

		buildFile
	}

	protected List<File> getClassPath()
	{
		if (!classpath)
		{
			def classpathRes = getClass().getClassLoader().getResource('plugin-classpath.txt')
			if (!classpathRes)
			{
				throw new FileNotFoundException('Не найден класс plugin-classpath.txt')
			}

			if (classpathRes)
			{
				new File(classpathRes.path).withReader { reader ->
					reader.eachLine { line ->
						classpath << new File(line)
					}
				}
			}
		}

		Collections.unmodifiableList(classpath)
	}
}

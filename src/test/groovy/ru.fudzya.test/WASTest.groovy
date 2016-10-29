package ru.fudzya.test
/**
 * @author fudzya
 * @since 26.10.2016
 */
abstract class WASTest
{
	protected File buildDir
	protected File buildFile

	protected final List<File> classpath = []

	protected File configureBuildFile(String testBuildFile)
	{
		if (buildFile)
		{
			def testBuild = getClass().getClassLoader().getResource(testBuildFile)
			if (!testBuild)
			{
				throw new FileNotFoundException("Не найден файл $testBuildFile")
			}

			testBuild.withReader { reader ->
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
				throw new FileNotFoundException('Не найден файл plugin-classpath.txt')
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

		classpath
	}

	void beforeClass()
	{
		def seconds = Calendar.getInstance().get(Calendar.SECOND)

		new File("${System.getProperty('java.io.tmpdir')}/testng_$seconds").with { directory ->

			if (directory.exists() || directory.mkdir())
			{
				buildDir = directory
			}

			buildDir
		}

		new File(buildDir, 'build.gradle').with { file ->

			if (file.exists() || file.createNewFile())
			{
				buildFile = file
			}

			buildFile
		}
	}

	void methodDown() throws IOException
	{
		if (buildFile)
		{
			println 'Очищаю файл сборки для следующей задачи'
			buildFile.text = ''
		}
	}

	void classDown() throws Exception
	{
		if (buildDir && buildDir.exists())
		{
			buildDir.deleteDir()
		}
	}
}

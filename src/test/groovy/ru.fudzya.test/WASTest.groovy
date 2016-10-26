/*
 * Copyright 2016 Ivan Balovtsev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

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

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

package ru.fudzya.was.help

import org.gradle.internal.impldep.com.google.common.io.Files
import org.gradle.internal.impldep.org.apache.commons.lang.SystemUtils

/**
 * @author fudzya
 * @since  05.11.2016
 */
class Crunches
{
	/**
	 * @param was     директория с установленной WebSphere AS
	 * @param closure замыкание которое необходимо выполнить после хака с копированием библиотек в папку bin
	 */
	static void withOSCrunches(String was, Closure closure)
	{
		if (!was)
		{
			return
		}

		final List<File> created = []
		try
		{
			if (SystemUtils.IS_OS_WINDOWS)
			{
				def bit = System.getenv('ProgramFiles(X86)') ? 64 : 32
				def bin = new File(was, '/bin')

				def binFiles = bin?.listFiles()?.collect { file ->
					file.name
				}

				def libFiles = new File(was, "/lib/native/win/x86_$bit")?.listFiles()?.findAll { file ->
					!(file.name in binFiles)
				}

				libFiles.each { from ->

					def to = new File(bin, from.name).with { file ->

						if (!file.exists())
						{
							file.createNewFile()
						}

						file
					}

					Files.copy(from, to)
					created << to
				}
			}

			closure.call()
		}
		finally
		{
			created.each { file -> file.deleteOnExit() }
		}
	}
}

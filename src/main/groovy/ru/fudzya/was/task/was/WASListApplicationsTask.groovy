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

package ru.fudzya.was.task.was

import ru.fudzya.was.WASConstants
import ru.fudzya.was.task.WASAdminTask

/**
 * @author fudzya
 * @since  23.10.2016
 */
class WASListApplicationsTask extends WASAdminTask
{
	void doExecute(Map<String, ?> arguments = [:])
	{
		super.doExecute(arguments)

		getAnt().taskdef(
			name      : 'listApplications',
			classname : WASConstants.CLASS_LIST_APPS,
			classpath : this.getClasspath())

		getAnt().listApplications(arguments)
	}
}

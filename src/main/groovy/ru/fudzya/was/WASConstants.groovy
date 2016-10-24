package ru.fudzya.was

/**
 * @author fudzya
 * @since  24.10.2016
 */
class WASConstants
{
	static final String EXTENSION_WAS       = 'was'
	static final String CONFIGURATION_WAS   = 'was'

	static final String TASK_LIST_APP       = 'wasApplications'
	static final String TASK_START_SERVER   = 'wasStartServer'

	static final String CLASS_START_SERVER  = 'com.ibm.websphere.ant.tasks.StartServer'
	static final String CLASS_LIST_APPS     = 'com.ibm.websphere.ant.tasks.ListApplications'

	private WASConstants() {}
}

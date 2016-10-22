package ru.fudzya.was.task

import org.gradle.api.internal.ConventionTask

/**
 * TODO javadoc
 *
 * @author fudzya
 * @since  23.10.2016
 */
abstract class WASAdminTask extends ConventionTask
{
	/**
	 *
	 */
	File wasHome

	/**
	 *
	 */
	String user

	/**
	 *
	 */
	String password

	/**
	 *
	 */
	String host

	/**
	 *
	 */
	int port

	/**
	 *
	 */
	String profileName = null

	/**
	 *
	 */
	ConnType connType

	/**
	 *
	 */
	String command

	/**
	 *
	 */
	String properties

	/**
	 *
	 */
	String profile

	/**
	 *
	 */
	String script

	/**
	 *
	 */
	Lang lang

	/**
	 *
	 */
	String fileEncoding = null

	/**
	 *
	 */
	boolean failOnError = false
}

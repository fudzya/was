package ru.fudzya.was.model

/**
 * Указывает на каком языке написан исполняемый скрипт
 *
 * @author fudzya
 * @since  23.10.2016
 */
enum Lang
{
	JACL  ('jacl'),
	JYTHON('jython')

	private final String lang
	Lang(String lang)
	{
		this.lang = lang
	}
}
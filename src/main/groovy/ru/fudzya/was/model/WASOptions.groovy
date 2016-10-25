package ru.fudzya.was.model

import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * @author fudzya
 * @since  24.10.2016
 */
class WASOptions implements Configurable<WASOptions>
{
	/**
	 * TCP порт
	 */
	String statusPort

	/**
	 * Устанавливает file.encoding для VM
	 */
	String fileEncoding

	/**
	 * Имя файла логов для логирования информации о запуске сервера
	 */
	String logFile

	/**
	 * Время ожидания
	 */
	String timeout

	/**
	 * Перезаписывать или нет существующий файл логов
	 */
	boolean replaceLog

	/**
	 * Закончить задачу не дожидаясь запуска сервера
	 */
	boolean noWait

	/**
	 * Печатать или нет информацию о статусе сервера
	 */
	boolean quiet

	/**
	 * Включить или выключить трассировку
	 */
	boolean trace

	/**
	 * Прервать выполнение задачи при ошибке
	 */
	boolean failOnError

	/**
	 * Скрипт исполняемый при запуске скрипта
	 */
	String startServerScript

	/**
	 * @param closure замыкание для настройки опций
	 * @return настроенный текущий экземпляр
	 */
	WASOptions configure(Closure closure)
	{
		ConfigureUtil.configureSelf(closure, this)
	}
}

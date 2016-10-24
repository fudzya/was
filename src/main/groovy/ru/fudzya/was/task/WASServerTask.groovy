package ru.fudzya.was.task

/**
 * @author fudzya
 * @since  24.10.2016
 */
abstract class WASServerTask extends WASTask
{
	/**
	 * Имя сервера
	 */
	String server

	/**
	 * Время ожидания
	 */
	String timeout

	/**
	 * TCP порт
	 */
	String statusPort

	/**
	 * Имя файла логов для логирования информации о запуске сервера
	 */
	String logFile

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

	WASServerTask()
	{
		super()
	}
}

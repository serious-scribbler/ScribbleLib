package de.pniehus.scribblerlib.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A custom formatter for java.util.logging.Logger
 * Formats log messages like this: [log level] time [source logger name] message<line separator>
 * @author Phil Niehus
 * (c) Copyright 2018 Phil Niehus
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 */
public class ScribblerLogFormat extends Formatter{
	private final String lineSeparator;
	private SimpleDateFormat dateFormat;
	
	/**
	 * Creates an instance of the ScribblerLogFormat using the given SimpleDateFormat to format time stamps
	 * @param dateFormat
	 */
	public ScribblerLogFormat(SimpleDateFormat dateFormat) {
		lineSeparator = System.getProperty("line.separator");
		this.dateFormat = dateFormat;
	}
	
	@Override
	public String format(LogRecord record) {
		StringBuffer bf = new StringBuffer(1000);
		bf.append("[" + record.getLevel() + "] ");
		bf.append(getFormatedDate(record.getMillis()));
		bf.append(" [" + record.getLoggerName() + "] ");
		bf.append(formatMessage(record));
		bf.append(lineSeparator);
		return bf.toString();
	}

	/**
	 * Returns the given date and time formated like this: [dd.MM.yyyy HH:mm:ss]
	 * @param millis Date and time as long
	 * @return
	 */
	private String getFormatedDate(long millis){
		return dateFormat.format(new Date(millis));
	}
}

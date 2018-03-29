package de.pniehus.scribblerlib.logging;

import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * This class implements a ConsoleHandler for loggers, that prints to stdout
 * @author Phil Niehus
 *
 */
public class ConsolePrintLogHandler extends Handler{
	
	@Override
	public void close() throws SecurityException {}

	@Override
	public void flush() {}

	@Override
	public void publish(LogRecord record) {
		try{
			String logMessage = getFormatter().format(record);
			System.out.print(logMessage);
		} catch(Exception e){
			reportError("No formatter!", e, ErrorManager.FORMAT_FAILURE);
		}
	}

}

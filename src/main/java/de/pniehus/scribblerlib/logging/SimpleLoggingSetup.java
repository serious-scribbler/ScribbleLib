package de.pniehus.scribblerlib.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains methods to setup logging in seconds
 * 
 * @author Phil Niehus 
 * (c) Copyright 2018 Phil Niehus
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 */
public class SimpleLoggingSetup {

	/**
	 * The default date format used for the setup of loggers
	 */
	public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	/**
	 * Sets up the root logger with the FileHandler, log level and
	 * {@link SimpleDateFormat}
	 * 
	 * @param logPath
	 *            The absolute path to the log file
	 * @param logLevel
	 *            The log level for the root handler
	 * @param enableStdout
	 *            Enables or disables output to stdout
	 * @param dateFormat
	 *            The format the logger is supposed to used for time stamps
	 * @throws IOException
	 *             When the selected file is not writable
	 * @throws SecurityException
	 *             when a security manager exists and the caller doesn't have
	 *             LogginPermission("control")
	 */
	public static void configureRootLogger(String logPath, Level logLevel, boolean enableStdout,
			SimpleDateFormat dateFormat) throws SecurityException, IOException {
		Logger root = Logger.getLogger("");
		if (root.getHandlers()[0] instanceof ConsoleHandler)
			root.removeHandler(root.getHandlers()[0]);
		FileHandler fileHandler = new FileHandler(logPath);
		fileHandler.setFormatter(new ScribblerLogFormat(dateFormat));
		root.addHandler(fileHandler);

		if (enableStdout) {
			ConsolePrintLogHandler ch = new ConsolePrintLogHandler();
			ch.setFormatter(new ScribblerLogFormat(dateFormat));
			root.addHandler(ch);
		}
	}

	/**
	 * Sets up the root logger with the FileHandler, log level and default date
	 * format
	 * 
	 * @param logPath
	 *            The absolute path to the log file
	 * @param logLevel
	 *            The log level for the root handler
	 * @param enableStdout
	 *            Enables or disables output to stdout
	 * @throws IOException
	 *             When the selected file is not writable
	 * @throws SecurityException
	 *             when a security manager exists and the caller doesn't have
	 *             LogginPermission("control")
	 */
	public static void configureRootLogger(String logPath, Level logLevel, boolean enableStdout)
			throws SecurityException, IOException {
		configureRootLogger(logPath, logLevel, enableStdout, DEFAULT_DATE_FORMAT);
	}

}

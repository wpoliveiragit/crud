package br.com.projeto.crud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	public static final String format25(String label) {
		return String.format("%-25s", label);
	}

	public static final Logger createLoggerSize30(Class<?> c) {
		return LoggerFactory.getLogger(String.format("%-30s", c.getSimpleName()));
	}

}

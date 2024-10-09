package br.com.projeto.crud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

	private Logger log;
	private Class<?> clazz;

	private LoggerUtils(String size, Class<?> clazz) {
		this.clazz = clazz;
		log = LoggerFactory.getLogger(String.format(size, clazz.getSimpleName()));
	}

	public static final LoggerUtils createLoggerSize30(Class<?> clazz) {
		return new LoggerUtils("%-30s", clazz);
	}

	public void infoLoadingBean() {
		log.info("-- LOADING BEAN >>> " + clazz.getSimpleName());
	}

	public void infoCreateBean() {
		log.info("-- CREATE BEAN >>> " + clazz.getSimpleName());
	}

	public void info(String format) {
		log.info(format);
	}

	/** {} -##- {} */
	public void infoFormatTwo(Object obj1, Object obj2) {
		log.info("{} -::- {}", obj1, obj2);
	}

	public void info(String format, Object... arguments) {
		log.info(format, arguments);
	}

}

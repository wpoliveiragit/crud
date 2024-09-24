package br.com.projeto.crud.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

public interface MethodUtils {

	MethodUtils METHOD = new MethodUtils() {
	};

	default String loadFile(String sqlFile) throws Exception {
		File file = ResourceUtils.getFile("classpath:" + sqlFile);
		return new String(Files.readAllBytes(Paths.get(file.toURI())));
	}

	default Map<String, String> loadSqlFile() throws Exception {
		Map<String, String> sqlMap = new HashMap<>(10);
		for (String sqlCommand : loadFile("scripts/SQLite.sql").split(";")) {
			String key = null;
			StringBuilder sql = new StringBuilder();
			for (String line : sqlCommand.split("\n")) {

				// LIMPEZA DE LINHAS
				if (line.isBlank() || line.isEmpty() || (line.length() > 1//
						&& line.substring(0, 2).equalsIgnoreCase("--"))) {
					continue;
				}

				// KEY
				if (line.charAt(0) == '#') {
					key = line;
					continue;
				}
				// TREIXO DO COMANDO
				sql.append(" " + line);
			}
			sqlMap.put(key, sql.append(";").toString());
		}
		return sqlMap;
	}
}

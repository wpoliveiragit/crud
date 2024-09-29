package br.com.projeto.crud.component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.projeto.crud.utils.Utils;
import lombok.Getter;

@Component
public class SqlCommandsComponent {
	private static final Logger LOG = Utils.createLoggerSize30(SqlCommandsComponent.class);

	private @Getter Map<String, String> sqlMap = new HashMap<>(1);

	public SqlCommandsComponent(@Value("${app.sqlite.scripts}") String sqlScripts) throws Exception {
		LOG.info("-- LOAD BEAN -- >> {}", SqlCommandsComponent.class.getSimpleName());

		LOG.info("---- LOADING SQL COMMANDS...");
		for (String sqlCommand : new String(
				Files.readAllBytes(Paths.get(ResourceUtils.getFile("classpath:" + sqlScripts).toURI()))).split(";")) {

			StringBuilder sql = new StringBuilder();
			String key = null;
			for (String row : sqlCommand.split("\n")) {
				if (uselessRow(row)) {
					continue;
				}
				if (row.startsWith("#")) {
					key = row; // Captura a chave
					continue;
				}
				sql.append(" ").append(row); // Adiciona o comando SQL
			}
			if (key == null) {
				continue;
			}
			sqlMap.put(key, sql.append(";").toString()); // add command
			LOG.info("---- {} >> {}", Utils.format25(key), sqlMap.get(key));
		}

	}

	/** verifica se a linha é inútil */
	private boolean uselessRow(String row) {
		return (row.isBlank() || row.isEmpty() || (row.length() > 1 && row.substring(0, 2).equalsIgnoreCase("--")));
	}

	/**
	 * Ajusta o commando sqls.
	 * 
	 * @param keyMap   A chave do comando sql no map.
	 * @param replaces as subistituições que o comando deve fazer.
	 * @return O comando ajustado.
	 */
	public String getAdjustedSqlCommand(String keyMap, String[]... replaces) {
		return Arrays.stream(replaces).reduce(sqlMap.get(keyMap),
				(result, replace) -> result.replace(replace[0], replace[1]), (s1, s2) -> s1);
	}
}

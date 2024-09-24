package br.com.projeto.crud.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.projeto.crud.utils.MethodUtils;
import lombok.Getter;

public class SQLiteConnectConfig {
	private static final Logger LOG = LoggerFactory.getLogger(SQLiteConnectConfig.class.getSimpleName());

	private @Getter Connection conn;
	private @Getter Statement stmt;
	private @Getter Map<String, String> sqlMap;

	private String sqlDropTable;

	public SQLiteConnectConfig(String dbName) {
		try {
			load();
			createConnect(dbName);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void load() throws Exception {
		sqlMap = MethodUtils.METHOD.loadSqlFile();
		sqlDropTable = sqlMap.get("#DROP_TABLE");
		LOG.info("ARQUIVO DE COMANDOS SQL CARREGADO");
	}

	private void createConnect(String dbName) throws Exception {
		conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/" + dbName);
		stmt = conn.createStatement();
		LOG.info("CONEXÃO ESTABILIZADA COM O DATABASE {}", dbName);
	}

	public void dropTable(String nameTable) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlDropTable.replace("?", nameTable));
			preparedStatement.executeUpdate();
			LOG.info("Tabela {} excluida com sucesso", nameTable);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}

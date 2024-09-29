package br.com.projeto.crud.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.utils.Utils;
import lombok.Getter;

@Component
public class SQLiteConnectComponent {
	private static final Logger LOG = Utils.createLoggerSize30(SQLiteConnectComponent.class);

	private SqlCommandsComponent sqlCommands;
	private @Getter Connection conn;
	private @Getter Statement stmt;

	public SQLiteConnectComponent(@Value("${app.sqlite.url}") String database,
			SqlCommandsComponent sqlCommandsComponent) throws Exception {
		LOG.info("-- LOAD BEAN -- >> {}", SQLiteConnectComponent.class.getSimpleName());

		this.sqlCommands = sqlCommandsComponent;
		{ // CRIA A CONEXÃO COM O BANCO DE DADO
			conn = DriverManager.getConnection(database);
			stmt = conn.createStatement();
			LOG.info("---- {} >> {}", Utils.format25("Create Connect"), database);
		}
	}

	/**
	 * Cria uma nova tabela.
	 * 
	 * @param replaces Os pares de subistituições do sql. Ex: > new String[] {
	 *                 "{table}", "TB_NAME" }).
	 * @throws Exception
	 */
	public void createTable(boolean printLog, String[]... replaces) throws Exception {
		String sql = sqlCommands.getAdjustedSqlCommand("#CREATE_TABLE", replaces);
		stmt.execute(sql);
		if (printLog) {
			LOG.info("{} >> {}", "Create Table", sql);
		}
	}

	/**
	 * Deleta uma tabela.
	 * 
	 * @param table O nome da tabela.
	 * @return O sql ajustado que foi usado.
	 * @throws Exception
	 */
	public void dropTable(boolean printLog, String[] table) throws Exception {
		String sql = sqlCommands.getAdjustedSqlCommand("#DROP_TABLE", table);
		conn.createStatement().executeUpdate(sql);
		if (printLog) {
			LOG.info("{} >> {}", "Drop Table", sql);
		}
	}

}

package br.com.projeto.crud.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.utils.LoggerUtils;
import lombok.Getter;

public @Component class SQLiteConnectComponent {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(SQLiteConnectComponent.class);

	private SqlCommandsComponent sqlCommands;
	private @Getter Connection conn;
	private @Getter Statement stmt;

	public SQLiteConnectComponent(@Value("${app.sqlite.url}") String database,
			SqlCommandsComponent sqlCommandsComponent) throws Exception {

		LOG.infoLoadingBean();
		// CRIA A CONEXÃO COM O BANCO DE DADO
		this.sqlCommands = sqlCommandsComponent;
		conn = DriverManager.getConnection(database);
		stmt = conn.createStatement();
		LOG.infoCreateBean();
	}

	public String createTable(String[]... replaces) throws Exception {
		String sql = sqlCommands.getAdjustedSqlCommand("#CREATE_TABLE", replaces);
		stmt.execute(sql);
		return sql;
	}

	public void createTableLog(String[]... replaces) throws Exception {
		LOG.infoFormatTwo("Create Table", createTable(replaces));
	}

	public String dropTable(String[] table) throws Exception {
		String sql = sqlCommands.getAdjustedSqlCommand("#DROP_TABLE", table);
		conn.createStatement().executeUpdate(sql);
		return sql;
	}

	public void dropTableLog(String[] table) throws Exception {
		LOG.infoFormatTwo("Drop Table", dropTable(table));
	}

}

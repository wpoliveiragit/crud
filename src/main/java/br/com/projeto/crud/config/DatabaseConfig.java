//package br.com.projeto.crud.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class DatabaseConfig {
//
//	private static final String JDBC_URL = "jdbc:sqlite:mydatabase.db";
//
//	private static HikariDataSource dataSource;
//
//	static {
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl(JDBC_URL);
//		config.setDriverClassName("org.sqlite.JDBC");
//		config.setMaximumPoolSize(10); // Tamanho máximo do pool de conexões
//		config.setConnectionTimeout(30000); // Tempo máximo de espera da conexão disponível (mili)
//		config.setIdleTimeout(600000); // Tempo ocioso máximo da conexão antes de ser removida do pool (mili)
//		config.setMaxLifetime(1800000); // Tempo máximo de vida de uma conexão no pool (em milissegundos)
//
//		// Inicializar o DataSource
//		dataSource = new HikariDataSource(config);
//	}
//
//	// Método para obter uma conexão do pool
//	public static Connection getConnection() throws SQLException {
//		return dataSource.getConnection();
//	}
//
//	// Fechar o DataSource quando a aplicação terminar
//	public static void close() {
//		if (dataSource != null) {
//			dataSource.close();
//		}
//	}
//}

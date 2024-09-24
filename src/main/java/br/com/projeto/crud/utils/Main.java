package br.com.projeto.crud.utils;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.projeto.crud.config.SQLiteConnectConfig;
import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.repository.dao.ItensDao;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("\n### Criação da conexão com o banco de dados");
		SQLiteConnectConfig sqLiteConnect = new SQLiteConnectConfig("sqliteDB.db");

		System.out.println("\n### Deletando a tabela TB_ITENS");
		sqLiteConnect.dropTable("TB_ITENS");

		System.out.println("\n### Criação do DAO");
		ItensDao dao = new ItensDao(sqLiteConnect);

		System.out.println("\n### INSERT");
		ItemModel item1 = new ItemModel();
		item1.setName("olga");
		dao.insert(item1);

		ItemModel item2 = new ItemModel();
		item2.setName("liliel");
		dao.insert(item2);

		ItemModel item3 = new ItemModel();
		item3.setName("gaspar");
		dao.insert(item3);

		System.out.println("\n### UPDATE");
		item3.setName("Gaspar");
		dao.update(item3);

		int id = 1;

		System.out.println("\n### DELETE");
		dao.delete(id).ifPresentOrElse(model -> System.out.println(model),
				() -> System.out.printf("id não encontrado %d\n", id));

		System.out.println("\n### FIND BY ID");
		dao.findById(id).ifPresentOrElse(model -> System.out.println(model),
				() -> System.out.printf("id não encontrado %d\n", id));

		System.out.println("\n### FIND ALL");
		dao.findAll().forEach(m -> System.out.println(m));

	}

	private static void createHikariDataSource(String url) throws Exception {
		int maxPoolSize = 10; // Tamanho máximo do pool de conexões
		int connectionTimeout = 30000; // Tempo máximo disponivel da conexão (mili)
		int idleTimeout = 600000; // Tempo ocioso máximo da conexão antes da remoção do pool (milli)
		int maxLifetime = 1800000; // Tempo máximo de vida de uma conexão no pool (mili)

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setDriverClassName("org.sqlite.JDBC");
		config.setMaximumPoolSize(maxPoolSize);
		config.setConnectionTimeout(connectionTimeout);
		config.setIdleTimeout(idleTimeout);
		config.setMaxLifetime(maxLifetime);

		// Inicializar o DataSource
		HikariDataSource dataSource = new HikariDataSource(config);
		Connection connection = dataSource.getConnection();
	}
}

package br.com.projeto.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBeansConfig {

	@Bean
	public SQLiteConnectConfig createSQLiteConnect() throws Exception {
		return new SQLiteConnectConfig("sqliteDB.db");
	}

}

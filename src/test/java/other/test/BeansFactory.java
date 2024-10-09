package other.test;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BeansFactory {

	/**
	 * Cria um datasource baseado nas configurações de 'app.datasource' no arquivo
	 * 'application.yml'.
	 * 
	 * @param env bean de controle de propriedades.
	 * @return O datasource referente a configuração no arquivo application.yml.
	 */
	public DataSource createDataSourcePostgreSql(Environment env) {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName(env.getProperty("app.datasource.postgresql.drive-class-name"));
		driver.setUrl(env.getProperty("app.datasource.postgresql.url"));
		driver.setUsername(env.getProperty("app.datasource.postgresql.user"));
		driver.setPassword(env.getProperty("app.datasource.postgresql.pass"));
		return driver;
	}

}

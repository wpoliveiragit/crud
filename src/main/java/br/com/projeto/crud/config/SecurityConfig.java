package br.com.projeto.crud.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.projeto.crud.dao.UserDao;
import br.com.projeto.crud.model.UserModel;
import br.com.projeto.crud.utils.LoggerUtils;

@Configuration
public class SecurityConfig {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(SecurityConfig.class);
	

	private @Autowired UserDao userDao;
	private final Map<String, String> users = new HashMap<>();

	public SecurityConfig() {
		// Adicione usuários e senhas ao Map
		users.put("user", new BCryptPasswordEncoder().encode("pass")); // Exemplo de usuário
	}

	@Bean // DEFINIÇÃO DE ACESSO DAS ROTAS
	public SecurityFilterChain securityFilterChain(HttpSecurity http, Environment env) throws Exception {
		LOG.infoLoadingBean();
		final String userPath = "/" + env.getProperty("app.controller.user.path") + "/**";

		final String swaggerDocs = "/v3/api-docs/**";
		final String swaggerUI = "/swagger-ui/**";
		final String swaggerHTML = "/swagger-ui.html";

		SecurityFilterChain ret = http.authorizeHttpRequests(authorizeRequests -> authorizeRequests

				// ROTAS COM PERMISSÃO DE ASSESSO SEM AUTENTICASSÃO (SWAGGER)
				.requestMatchers(swaggerDocs, swaggerUI, swaggerHTML).permitAll()

				// ROTAS COM PERMISSÃO DE ASSESSO SEM AUTENTICASSÃO (API)
				.requestMatchers(userPath).permitAll() //

				// DEFINE QUE TODAS AS OUTRAS ROTAS DEVEM SER AUTENTICADA
				.anyRequest().authenticated())

				// Desativar CSRF
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable()) //
				.build();
		
		
		LOG.infoCreateBean();
		return ret;
	}

	@Bean // VERIFICADOR DO LOGIN DE ACESSO
	public UserDetailsService userDetailsService() {
		return username -> {// VERIFICAÇÃO DA AUTENTICASSÃO
			
			String password = null;
			try {
 				Optional<UserModel> opt = userDao.findById(username);
 				password =  (opt.isPresent()) //
						? new BCryptPasswordEncoder().encode(opt.get().getPasswaord())//
						: null;
				
			} catch (Exception e) {
				return null;
			}
	
			
			return (password != null) //
					? User.builder().username(username).password(password).roles("USER").build()//
					: null;
		};
	}

	@Bean // DEFINE BCRYPT COMO O ALGORITMO DE CODIFICAÇÃO DE SENHA
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
package br.com.projeto.crud.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final Map<String, String> users = new HashMap<>();

	public SecurityConfig() {
		// Adicione usuários e senhas ao Map
		users.put("user", new BCryptPasswordEncoder().encode("pass")); // Exemplo de usuário
	}

	@Bean // DEFINIÇÃO DE ACESSO DAS ROTAS
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				// ROTAS COM PERMISSÃO DE ASSESSO SEM AUTENTICASSÃO (SUAGGER)
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				// ROTAS COM PERMISSÃO DE ASSESSO SEM AUTENTICASSÃO (API)
				.requestMatchers("/api/crud/**").permitAll() //
				// DEFINE QUE TODAS AS OUTRAS ROTAS DEVEM SER AUTENTICADA
				.anyRequest().authenticated())
				// Desativar CSRF
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable()) //
				.build();
	}

	@Bean // VERIFICADOR DO LOGIN DE ACESSO
	public UserDetailsService userDetailsService() {
		return username -> {// VERIFICAÇÃO DA AUTENTICASSÃO
			String password = users.get(username);
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
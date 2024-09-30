package br.com.projeto.crud.configuration;

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

	// Criar um Map para armazenar as credenciais de login
	private final Map<String, String> users = new HashMap<>();

	public SecurityConfig() {
		// Adicione usuários e senhas ao Map
		users.put("user", new BCryptPasswordEncoder().encode("pass")); // Exemplo de usuário
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				// Permitir acesso a algumas rotas sem autenticação
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				.requestMatchers("/api/crud/**").permitAll() // Adicione aqui as rotas do CRUD
				.anyRequest().authenticated() // Qualquer requisição que não esteja na lista acima deve ser autenticada
		).httpBasic(Customizer.withDefaults()) // Autenticação básica
				.csrf(csrf -> csrf.disable()); // Desativar CSRF

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			String password = users.get(username); // Buscar a senha do usuário no Map
			if (password != null) {
				return User.builder() // Retornar o UserDetails se o usuário existir
						.username(username).password(password).roles("USER").build();
			} else {
				// Retornar null se o usuário não existir
				return null;
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Define BCrypt como o algoritmo de codificação de senha
	}
}
package com.abandonedlabs.movierama.configuration;

import com.abandonedlabs.movierama.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Security configuration.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration  {

	private static final String[] WHITELIST_REGEX = {"/swagger-resources.*", "/swagger-ui.html"};

//	private static final String[] FRONTEND_WHITELIST_REGEX = {"/js.*", "/images.*", "/css.*", "/fonts.*", "/app.*"};
//
//	private static final String[] MOVIERAMA_WHITELIST_REGEX = new String[]{"/", "/api", "/version", "/health", "/ehcache.*", "/metrics.*"};

	private static final String USERS_API = "/users/**";
	private static final String MOVIES_API = "/movies/**";
	private static final String VOTES_API = "/votes/**";
	private static final String[] FRONTEND_API = { MOVIES_API, USERS_API, VOTES_API};
	/**
	 * The constant ROLE_USER.
	 */
	public static final String ROLE_USER = "ROLE_USER";

	/**
	 * Password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Filter chain security filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.requestMatchers(WHITELIST_REGEX).permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/users/*").authenticated()
				.requestMatchers(HttpMethod.GET, "/api/v1/movies").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/movies/likes").authenticated()
				.requestMatchers(HttpMethod.GET, "/api/v1/movies/hates").authenticated()
				.requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/users/id").authenticated()
				.requestMatchers(ROLE_USER).hasAnyRole("ADMIN", "USER")
				.anyRequest().permitAll()
				.and()
				.httpBasic();

		return http.build();
	}
}
package com.salesianostriana.dam.gestionhermandad.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Método que indica qué contenidos puede ver el admin, usuario logueado o sin
	 * loguear. También se expresa a qué plantilla se accede una vez se realiza el
	 * login o el logout y dónde dirigir la app en el caso de intentar acceder a un
	 * contenido protegido.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests()
				.antMatchers("/h2-console/**", "/img/**", "/css/**", "/js/**", "/webjars/**", "/", "/quienes_somos",
						"/index", "/login", "/registro", "/registro/submit")
						.permitAll()
				.antMatchers("/admin/**", "/user/**")
						.hasAnyRole("ADMIN")
				.antMatchers("/user/**")
						.hasAnyRole("USER")
						.anyRequest()
						.authenticated()
						.and()
				.formLogin()
						.loginPage("/login").defaultSuccessUrl("/")
						.permitAll()
						.and()
				.logout()
						.logoutUrl("/logout").logoutSuccessUrl("/")
						.permitAll()
						.and()
				.exceptionHandling().accessDeniedPage("/");

		http.csrf().disable();
		http.headers().frameOptions().disable();
		// @formatter:on
	}

}

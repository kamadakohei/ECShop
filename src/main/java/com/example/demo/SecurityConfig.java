package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//パスワードエンコーダーのBean定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//データソース
	@Autowired
	private DataSource dataSource;

	//ユーザーIDとパスワードを取得するSQL
	private static final String CUSTOMER_SQL = "SELECT customer_code, password, true FROM customers WHERE customer_code = ?";
	private static final String ROLE_SQL = "SELECT customer_code, role FROM customers WHERE customer_code = ?";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**", "/imgs/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			  .antMatchers("/webjars/**").permitAll()
			  .antMatchers("/css/**").permitAll()
			  .antMatchers("/login").permitAll()
			  .antMatchers("/customers").permitAll()
			  .antMatchers("/h2-console/**").permitAll()
			  .antMatchers("/").permitAll()
			  .anyRequest().authenticated();

		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.failureUrl("/login")
			.usernameParameter("userId")
			.passwordParameter("password")
			.defaultSuccessUrl("/", true);

		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("logout")
			.logoutSuccessUrl("/login");

		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			  .dataSource(dataSource)
			  .usersByUsernameQuery(CUSTOMER_SQL)
			  .authoritiesByUsernameQuery(ROLE_SQL)
			  .passwordEncoder(passwordEncoder());
	}

}

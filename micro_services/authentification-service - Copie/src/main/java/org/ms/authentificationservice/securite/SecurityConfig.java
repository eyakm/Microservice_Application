package org.ms.authentificationservice.securite;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig 
{
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	 {
		http.csrf().disable();//désactiver la protection contre CSRF
		 http.authorizeRequests().anyRequest().permitAll(); 
		 http.headers().frameOptions().disable();
	 	 return http.build();
	 }

}
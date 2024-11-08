package org.ms.authentificationservice.securite;

import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.filtres.JwtAuthenticationFilter;
import org.ms.authentificationservice.filtres.JwtAuthorizationFilter;
import org.ms.authentificationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	private UserService userService;

	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
		// utiliser les données de la BD
		auth.userDetailsService(new UserDetailsService() {
			@Override
			// Cette méthode est appeléé suite à la validation du formulaire
			// d'authentification
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				System.out.println("loadUserByUsername");

// récupérer la valeur de "username" pour récupérer un objet AppUser de la BD
				AppUser appUser = userService.getUserByName(username);

//construire une collection des rôles (permissions) selon le format de SpringSecurity
				Collection<GrantedAuthority> permissions = new ArrayList<>();
				// parcourir la liste des rôles de l'utilisateur pour remplir la collection des
				// permissions
				appUser.getRoles().forEach(r -> {
					permissions.add(new SimpleGrantedAuthority(r.getRoleName()));
				});
				// retourner un objet "User" de Spring Framework qui contient: "username",
				// "password" et les permissions
				return new User(appUser.getUsername(), appUser.getPassword(), permissions);
			}
		});
	}

	// Définir les règles d'accès
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable(); // désactiver la protection contre CSRF
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().disable();

		// afficher un formulaire d’authentification
		//http.formLogin();
		
		
   


		http.authorizeRequests().anyRequest().authenticated();
		
		//Ajouter un filtre pour l'authentification  
		 http.addFilter(new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));

		 // Ajouter un autre filtre qui doit s'exécuter en premlieu pour vérifier et autoriser
		 http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}

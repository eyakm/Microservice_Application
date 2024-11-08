package org.ms.authentificationservice;

import java.util.ArrayList;

import javax.management.relation.Role;

import org.ms.authentificationservice.entities.AppRole;
import org.ms.authentificationservice.entities.AppUser;

import org.ms.authentificationservice.repositories.AppRoleRepository;
import org.ms.authentificationservice.repositories.AppUserRepository;
import org.ms.authentificationservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthentificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationServiceApplication.class, args);}
		@Bean
		 CommandLineRunner start(  UserService userService, RepositoryRestConfiguration
		repositoryRestConfiguration) {
		 repositoryRestConfiguration.exposeIdsFor(AppRole.class);
		 return args -> {
	
	
		
			userService.addRole(new AppRole(null, "ROLE_USER"));
			userService.addRole(new AppRole(null, "ROLE_ADMIN"));
			


			userService.addUser(new AppUser("eya", "1234"));
			userService.addUser(new AppUser("mariem", "0000"));
			
			userService.addRoleToUser("eya", "ROLE_ADMIN");
			userService.addRoleToUser("mariem", "ROLE_USER");
			

			

		

	
};
}
		
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		
}

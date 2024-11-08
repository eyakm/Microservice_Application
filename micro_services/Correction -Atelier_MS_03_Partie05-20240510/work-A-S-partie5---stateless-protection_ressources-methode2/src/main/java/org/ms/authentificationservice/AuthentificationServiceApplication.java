package org.ms.authentificationservice;

import org.ms.authentificationservice.entities.AppRole;
import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.repositories.AppUserRepository;
import org.ms.authentificationservice.services.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity (prePostEnabled = true, securedEnabled = true)
public class AuthentificationServiceApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationServiceApplication.class, args);	
		
	}
	
	 @Bean
     CommandLineRunner start(UserService userService, 
   		  RepositoryRestConfiguration repositoryRestConfiguration  )
     {
        repositoryRestConfiguration.exposeIdsFor(AppUser.class);
        repositoryRestConfiguration.exposeIdsFor(AppRole.class);
        return args ->
        {
           userService.addUser(new AppUser(0L,"user1","123",null));
           userService.addUser(new AppUser(0L,"user2","456",null));
           
           userService.addRole(new AppRole(0L,"USER"));
           userService.addRole(new AppRole(0L,"ADMIN"));
           
           userService.addRoleToUser("user1", "USER");
           userService.addRoleToUser("user2", "USER");
           userService.addRoleToUser("user2", "ADMIN");



           //Afficher les clients existants dans la BD
           for (AppUser u : userService.getAllUsers())
              {
                 System.out.println(u.toString());
              }
        };
     }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	 }


}

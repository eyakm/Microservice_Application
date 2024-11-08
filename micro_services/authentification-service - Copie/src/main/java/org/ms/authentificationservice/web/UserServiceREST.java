
package org.ms.authentificationservice.web;

import java.net.URI;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.repositories.AppUserRepository;
import org.ms.authentificationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/")
public class UserServiceREST {
	private AppUserRepository userRepository;
	
	@Autowired
	public  UserServiceREST(AppUserRepository userRepository) {
	
	    this.userRepository = userRepository;
	
	}

	@GetMapping("/users")
	public List<AppUser> getUsers() {
		    return userRepository.findAll();

}



}
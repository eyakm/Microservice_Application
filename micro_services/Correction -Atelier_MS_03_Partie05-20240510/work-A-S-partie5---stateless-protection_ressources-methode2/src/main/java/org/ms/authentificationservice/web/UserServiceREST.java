package org.ms.authentificationservice.web;

import java.util.List;

import org.ms.authentificationservice.entities.AppRole;
import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


@RestController
public class UserServiceREST {
	 @Autowired
	    private UserService userService;
	 @GetMapping(path = "/users")
	 @PostAuthorize("hasAuthority('USER')")
	    public List<AppUser> listAllUsers()
	    {
	        return userService.getAllUsers();
	    }

	 @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	 @PostAuthorize("hasAuthority('ADMIN')")
		public AppUser addUser(@RequestBody AppUser appUser) {
			return userService.addUser(appUser);
		}

		@PostMapping(path = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostAuthorize("hasAuthority('ADMIN')")
		public AppRole addRole(@RequestBody AppRole appRole) {
			return userService.addRole(appRole);
		}

		@PostMapping(path = "/addRoleToUser")
		@PostAuthorize("hasAuthority('ADMIN')")
		public void addRoleToUser(@RequestBody UserRoleData userRoleData) {
			userService.addRoleToUser(userRoleData.getUsername(), userRoleData.getRoleName());
		}


}


@Data
class UserRoleData {
	private String username;
	private String roleName;
}


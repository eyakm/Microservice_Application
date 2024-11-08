package org.ms.authentificationservice.repositories;

import java.util.List;

import org.ms.authentificationservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface AppUserRepository  extends JpaRepository<AppUser,Long> {
	 AppUser findByUsername(String username); 
	 List<AppUser> findAll();

}

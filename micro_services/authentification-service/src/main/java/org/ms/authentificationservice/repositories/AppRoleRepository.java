package org.ms.authentificationservice.repositories;

import org.ms.authentificationservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
	AppRole findByRolename(String name); 

}



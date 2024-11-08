package org.ms.authentificationservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    public AppUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER ) // to load all the roles when loading the user
    private Collection<AppRole> roles = new ArrayList<>();

}

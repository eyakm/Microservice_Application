package org.ms.authentificationservice.services;
import java.util.List;
import org.ms.authentificationservice.entities.AppRole;
import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.repositories.AppRoleRepository;
import org.ms.authentificationservice.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

private  final AppUserRepository userRepository ;
    private final  AppRoleRepository roleRepository;
  //déclaration d’un attribut
  	private PasswordEncoder passwordEncoder=null;
   
    public UserServiceImpl(AppUserRepository userRepository, AppRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }


	@Override
	public AppUser addUser(AppUser appUser) {
	 appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
	 return userRepository.save(appUser);
	}

/*
 * 
 * @Override
public AppUser addUser(AppUser appUser) {
return userRepository.save(appUser);
}
 */
@Override
public AppRole addRole(AppRole appRole) {
return roleRepository.save(appRole);
}

@Override
public void addRoleToUser(String username, String roleName) {
AppUser user = userRepository.findByUsername(username);
        if (user != null) {
            AppRole role = roleRepository.findByRolename(roleName);
            if (role != null) {
                user.getRoles().add(role);
                userRepository.save(user);
            }
        }
}

@Override
public AppUser getUserByName(String username) {
return userRepository.findByUsername(username);
}

@Override
public List<AppUser> getAllUsers() {
return userRepository.findAll();
}



	



}
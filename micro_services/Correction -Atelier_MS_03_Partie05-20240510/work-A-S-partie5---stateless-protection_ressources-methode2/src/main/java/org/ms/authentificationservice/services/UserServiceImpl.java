package org.ms.authentificationservice.services;

import java.util.List;

import org.ms.authentificationservice.entities.AppRole;
import org.ms.authentificationservice.entities.AppUser;
import org.ms.authentificationservice.repositories.AppRoleRepository;
import org.ms.authentificationservice.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private PasswordEncoder passwordEncoder=null;
	
	public UserServiceImpl(PasswordEncoder passwordEncoder) 
	{
		this.passwordEncoder=passwordEncoder;		
	}
	

	@Autowired
	private AppRoleRepository appRoleRepository;
	
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	
	@Override
	public AppUser addUser(AppUser appUser) {
		// TODO Auto-generated method stub

	    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return appUserRepository.save(appUser);
	}

	@Override
	public AppRole addRole(AppRole appRole) {
		// TODO Auto-generated method stub
		return appRoleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole =appRoleRepository.findByRoleName(roleName);
		appUser.getRoles().add(appRole);
		appUserRepository.save(appUser);

	}

	@Override
	public AppUser getUserByName(String username) {
		// TODO Auto-generated method stub
		return  appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getAllUsers() {
		// TODO Auto-generated method stub
		return appUserRepository.findAll();
	}

}

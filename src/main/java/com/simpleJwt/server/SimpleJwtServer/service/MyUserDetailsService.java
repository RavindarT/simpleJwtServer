package com.simpleJwt.server.SimpleJwtServer.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simpleJwt.server.SimpleJwtServer.repos.UserDetailsCrud;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	public UserDetailsCrud userDetailsCrud;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Exists:::"+userDetailsCrud.existsById(username));
		if (userDetailsCrud.existsById(username) ) { 
			Optional<com.simpleJwt.server.SimpleJwtServer.repos.data.UserDetails> userDetails = userDetailsCrud.findById(username);
			System.out.println(userDetails.get().getPassWord());
			return new User(username, userDetails.get().getPassWord(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
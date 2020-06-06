package com.simpleJwt.server.SimpleJwtServer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.simpleJwt.server.SimpleJwtServer.repos.data.UserDetails;

public interface UserDetailsCrud extends JpaRepository<UserDetails, String>{

}

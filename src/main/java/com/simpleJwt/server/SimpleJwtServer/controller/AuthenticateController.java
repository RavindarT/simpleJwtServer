package com.simpleJwt.server.SimpleJwtServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simpleJwt.server.SimpleJwtServer.config.TokenUtil;
import com.simpleJwt.server.SimpleJwtServer.exception.Details;

import com.simpleJwt.server.SimpleJwtServer.model.UserNamePassword;
import com.simpleJwt.server.SimpleJwtServer.reposMongoDB.BooksRepository;
import com.simpleJwt.server.SimpleJwtServer.reposMongoDB.data.Books;
import com.simpleJwt.server.SimpleJwtServer.service.MyUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/hello")
public class AuthenticateController {
	
	@Autowired
	public com.simpleJwt.server.SimpleJwtServer.reposStgDB.StudentRepository studentRepository;
	
	@Autowired
	public BooksRepository booksRepository;
	
	@GetMapping("/welcomeMessage")
	public ResponseEntity<String> getMessage(@RequestParam(name = "i") Integer i){
		if ( i == 1)
		throw new Details();
		List students = (List) studentRepository.findAll();
		System.out.println("list:::"+students.size());
		List<Books> books  =booksRepository.findAll();
		System.out.println("Books..."+books.size());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> createAuthenticationToken(@RequestBody UserNamePassword authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = tokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new String(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println(username+":::"+password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

package com.pitang.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafio.authorization.JwtUtl;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.exception.LoginException;
import com.pitang.desafio.exception.NotFoundException;
import com.pitang.desafio.model.AuthenticationRequest;
import com.pitang.desafio.model.AuthenticatiorResponse;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.service.UserService;

@RequestMapping("carsystem/api")
@RestController
public class UserDetailController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtl jwtUtil;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value ="/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws LoginException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getLogin(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new LoginException("Login ou senha incorreto");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
		
		 Usuario usuario = userService.findByLogin(authenticationRequest.getLogin());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticatiorResponse(jwt,authenticationRequest.getLogin(),usuario.getIdUser(),true));
		
	}

	
	@RequestMapping(value = "/me/{login}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("login") String login) throws NotFoundException,BadRequestException  {
			return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
	}
	
	
}
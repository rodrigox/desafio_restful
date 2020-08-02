package com.pitang.desafio.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafio.authorization.JwtUtl;
import com.pitang.desafio.model.AuthenticationRequest;
import com.pitang.desafio.model.AuthenticatiorResponse;

//@RequestMapping("")
@RestController
public class AboutUserService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtl jwtUtil;

	@RequestMapping(value ="/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getLogin(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Login ou senha incorreto");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticatiorResponse(jwt));
		
	}

}
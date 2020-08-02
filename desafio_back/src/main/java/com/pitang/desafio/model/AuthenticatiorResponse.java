package com.pitang.desafio.model;

import java.io.Serializable;

public class AuthenticatiorResponse implements Serializable{

	private final String jwt;

	
	
	public AuthenticatiorResponse(String jwt) {
		super();
		this.jwt = jwt;
	}



	public String getJwt() {
		return jwt;
	}
	
	
	
}

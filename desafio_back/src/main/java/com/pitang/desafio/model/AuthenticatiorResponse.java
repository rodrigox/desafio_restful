package com.pitang.desafio.model;

import java.io.Serializable;

public class AuthenticatiorResponse implements Serializable{

	private final String token;
	private final String login;
	private final Boolean sucess;
	private final Long idUser;

	
	
	public AuthenticatiorResponse(String token,String login,Long idUser,Boolean sucess) {
		super();
		this.login = login;
		this.token = token;
		this.sucess = true;
		this.idUser = idUser;
	}


	
	public Boolean getSucess() {
		return sucess;
	}



	public Long getIdUser() {
		return idUser;
	}



	public String getToken() {
		return token;
	}
	
	public String getLogin() {
		return login;
	}
	
}

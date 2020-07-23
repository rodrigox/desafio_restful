package com.pitang.desafio.dto;

import java.util.List;

import com.pitang.desafio.model.Carro;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.util.DateUtil;


public class UsuarioDTOIn {
	private  Long idUser;
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String birthday;
	private  String login;
	private  String password;
	private  String phone;
	
	private  List<Carro>cars;
	

	public UsuarioDTOIn(Long idUser,String firstName, String lastName, String email, String birthday, String login, String password,
			String phone, List<Carro> cars) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
	}





	public Usuario transformToUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setIdUser(idUser);
		usuario.setFirstName(firstName);
		usuario.setLastName(lastName);
		usuario.setEmail(email);
		usuario.setBirthday(DateUtil.stringToDate(birthday));
		usuario.setLogin(login);
		usuario.setPassword(password);
		usuario.setPhone(phone);
		usuario.setCars(cars);

		
		return usuario; 
	}




	
}

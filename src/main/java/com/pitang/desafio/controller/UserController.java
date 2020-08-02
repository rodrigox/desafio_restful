package com.pitang.desafio.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.exception.NotFoundException;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.repository.UsuarioDAO;



@Controller
public class UserController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
	public Iterable<?> getAll() {
		return usuarioDAO.findAll();
	}
	public  void   save(Usuario user) throws BadRequestException {
		
		//validateFields(user);
		
		if(emailExists(user)) {
			throw new BadRequestException("email already exists");
		}else if(loginExists(user)) {
			throw new BadRequestException("Login already exists");

		}
		
		
		user.setCreationDate(Calendar.getInstance().getTime());
		usuarioDAO.save(user);
	}
	
	public void delete(Long id) throws NotFoundException,BadRequestException {
		getById(id);
		usuarioDAO.deleteById(id);
	}
	
	public  Usuario  getById(Long id) throws NotFoundException,BadRequestException {
		if(id ==null) {
			throw new BadRequestException("User id Can't be null");
		}
		Optional<Usuario> returned = usuarioDAO.findById(id);
		if (returned.isPresent()) {
			return returned.get();
		}else {
			throw new NotFoundException("User not found");
		}

	}
	
	public Boolean emailExists(Usuario user) {
		List<Usuario> uList =  usuarioDAO.findByEmail(user.getEmail());
		return  uList==null || uList.isEmpty()  ? false: true;
	}
	public Boolean loginExists(Usuario user) {
		Usuario userReturned =  usuarioDAO.findByLogin(user.getLogin());
		return  userReturned==null  ? false: true;
		
	}
	
	
	public Usuario findByLogin(String login) {
		
		if(login ==null || login.isEmpty()) {
			throw new BadRequestException("User login Can't be null");
		}
		Usuario returned = usuarioDAO.findByLogin(login);
		if (returned!=null) {
			return returned;
		}else {
			throw new NotFoundException("User not found");
		}
		
	}
	
	public Usuario update(Usuario user) throws NotFoundException,BadRequestException {
		Usuario usuario = getById(user.getIdUser());

		usuario.setFirstName(user.getFirstName());
		usuario.setLastName(user.getLastName());
		usuario.setEmail(user.getEmail());
		usuario.setBirthday(user.getBirthday());
		usuario.setLogin(user.getLogin());
		usuario.setPhone(user.getPhone());

		return usuarioDAO.save(usuario);
	}
	
	
	
	private void validateFields(Usuario user) throws BadRequestException{
		if((StringUtils.isAnyBlank(user.getFirstName(),
			user.getLastName(),user.getEmail(),
			user.getLogin(),user.getPhone())) 
			|| user.getBirthday()==null) {
			throw new  BadRequestException("Missing fields from Usuario");
		}
		
	}
}

package com.pitang.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import com.pitang.desafio.exception.AppGenericException;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.repository.UsuarioDAO;



@Controller
public class UserController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
	public Iterable<?> getAll() {
		return usuarioDAO.findAll();
	}
	public  void   save(Usuario user) throws Exception {
		
		validateFields(user);
		
		if(emailExists(user)) {
			throw new AppGenericException("email already exists");
		}else if(loginExists(user)) {
			throw new AppGenericException("Login already exists");

		}
		
		usuarioDAO.save(user);
	}
	
	public void delete(Long id) throws Exception {
		getById(id);
		usuarioDAO.deleteById(id);
	}
	
	public  Usuario  getById(Long id) throws Exception {
		if(id ==null) {
			throw new AppGenericException("User id Can't be null");
		}
		Optional<Usuario> returned = usuarioDAO.findById(id);
		if (returned.isPresent()) {
			return returned.get();
		}else {
			throw new AppGenericException("User not found");
		}

	}
	
	public Boolean emailExists(Usuario user) {
		List<Usuario> uList =  usuarioDAO.findByEmail(user.getEmail());
		return  uList==null || uList.isEmpty()  ? false: true;
	}
	public Boolean loginExists(Usuario user) {
		List<Usuario> uList =  usuarioDAO.findByLogin(user.getLogin());
		return  uList==null || uList.isEmpty()  ? false: true;
		
	}
	
	public Usuario update(Usuario user) throws Exception {
		Usuario usuario = getById(user.getIdUser());

		usuario.setFirstName(user.getFirstName());
		usuario.setLastName(user.getLastName());
		usuario.setEmail(user.getEmail());
		usuario.setBirthday(user.getBirthday());
		usuario.setLogin(user.getLogin());
		usuario.setPhone(user.getPhone());

		return usuarioDAO.save(usuario);
	}
	
	
	
	private void validateFields(Usuario user) throws Exception{
		if((StringUtils.isAnyBlank(user.getFirstName(),
			user.getLastName(),user.getEmail(),
			user.getLogin(),user.getPhone())) 
			|| user.getBirthday()==null) {
			throw new  AppGenericException("Missing fields from Usuario");
		}
		
	}
}

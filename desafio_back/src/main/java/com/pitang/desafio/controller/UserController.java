package com.pitang.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public  void   save(Usuario user) {
		usuarioDAO.save(user);
	}
	
	public  void  delete(Long id) {
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
	
	public Boolean emailExists(String email) {
		List<Usuario> uList =  usuarioDAO.findByEmail(email);
		return  uList==null || uList.isEmpty()  ? false: true;
	}
	public Boolean loginExists(String email) {
		List<Usuario> uList =  usuarioDAO.findByLogin(email);
		return  uList==null || uList.isEmpty()  ? false: true;
		
	}
	
	public  Usuario  update(Usuario user) throws Exception {
		Usuario usuario =  getById(user.getIdUser());
		
		usuario.setFirstName(user.getFirstName());
		usuario.setLastName(user.getLastName());
		user.setEmail(user.getEmail());
		user.setBirthday(user.getBirthday());
		user.setLogin(user.getLogin());
		user.setPhone(user.getPhone());
		
		return usuarioDAO.save(usuario);
	}
	
}

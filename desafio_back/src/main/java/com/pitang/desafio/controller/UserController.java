package com.pitang.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
	public  Usuario  getById(Long id) {
		return usuarioDAO.getOne(id);
	}
	
	public Boolean emailExists(String email) {
		List<Usuario> uList =  usuarioDAO.findByEmail(email);
		return  uList==null || uList.isEmpty()  ? false: true;
	}
	public Boolean loginExists(String email) {
		List<Usuario> uList =  usuarioDAO.findByLogin(email);
		return  uList==null || uList.isEmpty()  ? false: true;
		
	}
	
	public  Usuario  update(Usuario user) {
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

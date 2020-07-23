package com.pitang.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.desafio.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long>{

	
	
	List<Usuario>findByLogin(String name);
	
	List<Usuario>findByEmail(String name);


}

package com.pitang.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.desafio.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long>{

}

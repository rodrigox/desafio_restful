package com.pitang.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.desafio.model.Carro;

public interface CarDAO extends JpaRepository<Carro, Long> {

}

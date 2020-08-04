package com.pitang.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.desafio.model.Carro;

public interface CarRepository extends JpaRepository<Carro, Long> {

	
	List<Carro> findByLicensePlate(String licensePlate);
 

}

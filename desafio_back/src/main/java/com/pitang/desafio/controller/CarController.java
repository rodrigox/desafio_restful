package com.pitang.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pitang.desafio.model.Carro;
import com.pitang.desafio.repository.CarDAO;

@Controller
public class CarController {

	@Autowired
	private CarDAO carDAO;
	
	
	public Iterable<?> getAll() {
		return carDAO.findAll();
	}
	public  void save(Carro car) {
		carDAO.save(car);
	}
	
	
}

package com.pitang.desafio.dto;

import com.pitang.desafio.model.Carro;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.util.Util;

public class CarroDTO {

	private Long idCar;
	private Integer year;
	private String licensePlate ;
	private String model;
	private String color;
	
	private Usuario usuario;

	public CarroDTO(Long idCar, Integer year, String licensePlate, String model, String color, Long idUser) {
		super();
		usuario = new Usuario();
		this.idCar = idCar;
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
		this.usuario.setIdUser(idUser);
	}
	
	
	
public Carro transformToCarro() {
		
		Carro carro = new Carro();
		carro.setIdCar(idCar);
		carro.setLicensePlate(licensePlate);
		carro.setModel(model);
		carro.setYear(year);
		carro.setColor(color);

		carro.setUsuario(usuario);
		
		
		return carro; 
	}


	
	
}

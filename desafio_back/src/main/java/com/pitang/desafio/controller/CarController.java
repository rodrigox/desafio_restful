package com.pitang.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.pitang.desafio.exception.AppGenericException;
import com.pitang.desafio.model.Carro;
import com.pitang.desafio.repository.CarDAO;

@Controller
public class CarController {

	@Autowired
	private CarDAO carDAO;
	
	
	public Iterable<?> getAll() {
		return carDAO.findAll();
	}
	public  void save(Carro car) throws AppGenericException {

		validateFields(car);
		if(licensePlateExists(car)) {
            throw new AppGenericException("license Plate Already exists");
		}
		
		carDAO.save(car);
	}
	
	private void validateFields(Carro car) throws AppGenericException {
		if (StringUtils.isAnyBlank(car.getColor(), car.getLicensePlate(), 
				car.getModel()) || car.getYear() == null
				|| car.getUsuario().getIdUser() == null) {
			
             throw new AppGenericException("Missing fields from Car ");
		}
	}
	
	public Boolean licensePlateExists(Carro car) {
		List<Carro> carros =  carDAO.findByLicensePlate(car.getLicensePlate());
		return  carros==null || carros.isEmpty()  ? false: true;
	}
}

package com.pitang.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.model.Carro;
import com.pitang.desafio.repository.CarDAO;

@Controller
public class CarController {

	@Autowired
	private CarDAO carDAO;
	
	
	public Iterable<?> getAll() {
		return carDAO.findAll();
	}
	public  void save(Carro car) throws BadRequestException {

		validateFields(car);
		if(licensePlateExists(car)) {
            throw new BadRequestException("license Plate Already exists");
		}
		
		carDAO.save(car);
	}
	
	private void validateFields(Carro car) throws BadRequestException {
		if (StringUtils.isAnyBlank(car.getColor(), car.getLicensePlate(), 
				car.getModel()) || car.getYear() == null
				|| car.getUsuario().getIdUser() == null) {
			
             throw new BadRequestException("Missing fields from Car ");
		}
	}
	
	
	public Carro getById(Long id) throws BadRequestException {

		
		if(id ==null) {
			throw new BadRequestException("Car id Can't be null");
		}
		Optional<Carro> returnedValue = carDAO.findById(id);
		if (returnedValue.isPresent()) {
			return returnedValue.get();
		}else {
			throw new BadRequestException("Car not found");
		}
	}
	
	public void delete(Long id) throws BadRequestException {
		
		getById(id);
		carDAO.deleteById(id);

	}
	
	public void update(Carro car) throws BadRequestException {
		Carro carro = getById(car.getIdCar());

		carro.setColor(car.getColor());
		carro.setLicensePlate(car.getLicensePlate());
		carro.setModel(car.getModel());
		carro.setYear(car.getYear());
		
		carDAO.save(carro);

	}
	
	public Boolean licensePlateExists(Carro car) {
		List<Carro> carros =  carDAO.findByLicensePlate(car.getLicensePlate());
		return  carros==null || carros.isEmpty()  ? false: true;
	}
}
package com.pitang.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.model.Carro;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.repository.CarRepository;
import com.pitang.desafio.repository.UserRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Iterable<?> getAll() {
		return carRepository.findAll();
	}
	
	private  List<Carro> getAllCarsFromUser(Long idUser) throws BadRequestException {
		 Optional<Usuario> optional=  userRepository.findById(idUser);
		 if(optional.isPresent()) {
			return optional.get().getCars();
		 }else {
			 throw new BadRequestException(" Usuario não encontrado ");
		 }
		 
		 
		
	}
	public  void save(Carro car) throws BadRequestException {

		validateFields(car);
		if(licensePlateExists(car)) {
            throw new BadRequestException("license Plate Already exists");
		}
		
		carRepository.save(car);
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
		Optional<Carro> returnedValue = carRepository.findById(id);
		if (returnedValue.isPresent()) {
			return returnedValue.get();
		}else {
			throw new BadRequestException("Car not found");
		}
	}
	
	public void delete(Long id) throws BadRequestException {
		
		getById(id);
		carRepository.deleteById(id);

	}
	
	public void update(Carro car) throws BadRequestException {
		Carro carro = getById(car.getIdCar());

		
		List<Carro> carPlate = carRepository.findByLicensePlate(car.getLicensePlate());
		
		if(carPlate!=null  && !carPlate.isEmpty() 
				&&  carPlate.get(0).getIdCar()!= car.getIdCar()) {
			throw new BadRequestException("License plate already exists");
		}
		
		
		carro.setColor(car.getColor());
		carro.setLicensePlate(car.getLicensePlate());
		carro.setModel(car.getModel());
		carro.setYear(car.getYear());
		
		carRepository.save(carro);

	}
	
	public Boolean licensePlateExists(Carro car) {
		List<Carro> carros =  carRepository.findByLicensePlate(car.getLicensePlate());
		return  carros==null || carros.isEmpty()  ? false: true;
	}
}

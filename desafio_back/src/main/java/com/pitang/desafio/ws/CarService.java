package com.pitang.desafio.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafio.controller.CarController;
import com.pitang.desafio.dto.CarroDTO;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.model.Carro;

@RequestMapping("carsystem/api")
@RestController
public class CarService {

	
	@Autowired
	private CarController carController;
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(carController.getAll(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping (value = "/car", method = RequestMethod.POST)
	public  ResponseEntity<?> save(@RequestBody CarroDTO dto) throws Exception {
		Carro car = dto.transformToCarro();
		
		userService.getUser(car.getUsuario().getIdUser());
		carController.save(dto.transformToCarro());
		return new ResponseEntity<List<Carro>>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/car/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCar(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<>(carController.getById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws BadRequestException {
		
		carController.delete(id);
		return new ResponseEntity<List<Carro>>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/car", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody CarroDTO dto) throws BadRequestException {
		
		carController.update(dto.transformToCarro());
		return new ResponseEntity<List<Carro>>(HttpStatus.OK);
	}
	
}

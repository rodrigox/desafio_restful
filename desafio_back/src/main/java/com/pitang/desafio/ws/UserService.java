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

import com.pitang.desafio.controller.UserController;
import com.pitang.desafio.dto.UsuarioDTOIn;
import com.pitang.desafio.model.Usuario;

@RequestMapping("carsystem/api")
@RestController
public class UserService {

	@Autowired
	private UserController userController;
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(userController.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping (value = "/user", method = RequestMethod.POST)
	public  ResponseEntity<?> save(@RequestBody UsuarioDTOIn dto) {
		userController.save(dto.transformToUsuario());
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<>(userController.getById(id), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody UsuarioDTOIn dto) throws Exception {
		
		userController.update(dto.transformToUsuario());
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		
		userController.delete(id) ;
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
		
	}
}

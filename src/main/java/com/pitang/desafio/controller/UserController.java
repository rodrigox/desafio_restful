package com.pitang.desafio.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pitang.desafio.dto.UsuarioDTOIn;
import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.exception.NotFoundException;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.service.UserService;

@RequestMapping("carsystem/api")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping (value = "/user", method = RequestMethod.POST)
	public  ResponseEntity<?> save(@RequestBody UsuarioDTOIn dto) throws BadRequestException {
		userService.save(dto.transformToUsuario());
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws NotFoundException,BadRequestException  {
			return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody UsuarioDTOIn dto) throws NotFoundException,BadRequestException {
		
		userService.update(dto.transformToUsuario());
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException,BadRequestException {
		
		userService.delete(id) ;
		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
		
	}
}

package com.pitang.desafio.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafio.exception.NotFoundException;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.repository.UsuarioDAO;

@Service
public class MyUserDetailService implements UserDetailsService{

	

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		
		Usuario user = usuarioDAO.findByLogin(userName);
		
		//return new User(user.getLogin(),user.getPassword(),new ArrayList<>());
		return new User("aaa","sss",new ArrayList<>());

	}

	
	
	
	
	
}

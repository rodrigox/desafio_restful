package com.pitang.desafio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300097025056273L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser; 
	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private String login;
	private String password;
	private String phone;
	private Date creationDate;
	private Date lastLogin;
	
	@JsonManagedReference
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Carro> cars;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Carro> getCars() {
		return cars;
	}

	public void setCars(List<Carro> cars) {
		this.cars = cars;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	
	
}

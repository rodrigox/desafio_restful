package com.pitang.desafio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Carro implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6456293883587296771L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCar;
	private Integer year;
	private String licensePlate;
	private String model;
	private String color;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
	public Carro() {
		
	}
	public Long getIdCar() {
		return idCar;
	}
	public void setIdCar(Long idCar) {
		this.idCar = idCar;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((idCar == null) ? 0 : idCar.hashCode());
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (idCar == null) {
			if (other.idCar != null)
				return false;
		} else if (!idCar.equals(other.idCar))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
	
	
	
}

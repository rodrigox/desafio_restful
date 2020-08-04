package com.pitang.desafio.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pitang.desafio.exception.BadRequestException;
import com.pitang.desafio.model.Carro;
import com.pitang.desafio.model.Usuario;
import com.pitang.desafio.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceTest {
	
	@InjectMocks
	private CarService carService;
	
	@Mock
	private CarRepository carRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Rule 
	public ErrorCollector errorCollector = new ErrorCollector();
	
	Carro toSaveCar = new Carro();
	Carro savedCar = new Carro();
	Usuario usuario = new Usuario();

	@Before
	public void setup() {
		initMocks(this);
		
		usuario.setIdUser(1L);
		toSaveCar.setColor(InfoMock.COLOR);
		toSaveCar.setLicensePlate(InfoMock.LICENSE_PLATE);
		toSaveCar.setModel(InfoMock.MODELO);
		toSaveCar.setYear(InfoMock.YEAR);
		toSaveCar.setUsuario(usuario);
		
		savedCar.setColor(InfoMock.COLOR);
		savedCar.setIdCar(1L);
		savedCar.setLicensePlate(InfoMock.LICENSE_PLATE);
		savedCar.setModel(InfoMock.MODELO);
		savedCar.setYear(InfoMock.YEAR);
		savedCar.setUsuario(usuario);
	}
	
	@Test
	public void saveCar(){
		
		savedCar.setIdCar(1L);
        when(carRepository.save(toSaveCar)).thenReturn(savedCar);
		
		when(carRepository.findByLicensePlate(toSaveCar.getLicensePlate()))
		.thenReturn(new ArrayList<>());
		
		carService.save(toSaveCar);
		
		errorCollector.checkThat(savedCar.getIdCar(), is(1L));
		errorCollector.checkThat(savedCar.getLicensePlate(), is(InfoMock.LICENSE_PLATE));
		errorCollector.checkThat(savedCar.getModel(), is(InfoMock.MODELO));
		errorCollector.checkThat(savedCar.getColor(), is(InfoMock.COLOR));
		errorCollector.checkThat(savedCar.getYear(), is(InfoMock.YEAR));
		
	}
	
	@Test
	public void returnExceptionWhenEmailAndCarExist() {		
		
		List<Carro> carros = new ArrayList<>();
		carros.add(savedCar);
		when(carRepository.findByLicensePlate(toSaveCar.getLicensePlate()))
		.thenReturn(carros);		
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("license Plate Already exists");		
		carService.save(toSaveCar);
	}
	
	
	@Test
	public void removeCarById() {		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCar));
		carService.delete(1L);
		verify(carRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void getCarById() {
		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCar));
		carService.getById(1L);
		
		errorCollector.checkThat(savedCar.getIdCar(), is(1L));
	}
	
	
	
	@Test
	public void deleteCarById() {		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCar));
		carService.delete(1L);
		verify(carRepository, times(1)).deleteById(1L);
	}
	
	
	
	@Test
	public void returnExceptionWhenRemoveIdNoExist() {				
		when(carRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("Car not found");
		carService.delete(1L);
	}
	
	
	@Test
	public void returnExceptionIfIdNotExistOnFind() {
				
		when(carRepository.findById(1L)).thenReturn(Optional.empty());
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("Car not found");
		carService.getById(1L);
	}
	
	
	
	@Test
	public void updateCar() {
		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCar));
		
		
		carService.update(savedCar);
		
		errorCollector.checkThat(savedCar.getIdCar(), is(1L));
		errorCollector.checkThat(savedCar.getLicensePlate(), is(InfoMock.LICENSE_PLATE));
		errorCollector.checkThat(savedCar.getModel(), is(InfoMock.MODELO));
		errorCollector.checkThat(savedCar.getColor(), is(InfoMock.COLOR));
		errorCollector.checkThat(savedCar.getYear(), is(InfoMock.YEAR));
	}
	
	@Test
	public void shouldReturnExceptionWhenTryUpdateACarAndPlateAlreadyExist() {
		Carro carro = new Carro();
		
		carro.setColor(InfoMock.COLOR);
		carro.setIdCar(2L);
		carro.setLicensePlate(InfoMock.LICENSE_PLATE);
		carro.setModel(InfoMock.MODELO);
		carro.setYear(InfoMock.YEAR);
		carro.setUsuario(usuario);
		List<Carro> carros = new ArrayList<>();
		carros.add(carro);
		
		when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCar));
		when(carRepository.findByLicensePlate(savedCar.getLicensePlate()))
		.thenReturn(carros);		
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("License plate already exists");		
		carService.update(savedCar);
	}
	
}
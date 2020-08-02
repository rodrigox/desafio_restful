/*
 * package com.pitang.desafio.ws;
 * 
 * import java.util.Date;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.security.test.context.support.WithMockUser; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.test.web.servlet.MockMvc;
 * 
 * import com.pitang.desafio.dto.CarroDTO; import
 * com.pitang.desafio.model.Carro; import com.pitang.desafio.model.Usuario;
 * import com.pitang.desafio.repository.CarDAO;
 * 
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @WebMvcTest(value = CarService.class)
 * 
 * @WithMockUser public class CarServiceTest {
 * 
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @MockBean private CarService carService;
 * 
 * 
 * @Test public void save()throws Exception{
 * 
 * CarroDTO mockCar = new CarroDTO (null, 2012, "USB-1234", "Audi", "Black",
 * 1L);
 * 
 * Mockito.when( carService.save(CarroDTO.class).thenReturn(mockCar)); }
 * 
 * }
 */
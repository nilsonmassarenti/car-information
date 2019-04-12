package com.nilsonmassarenti.carinformationservice;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilsonmassarenti.carinformationservice.controller.CarInformationController;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationBulkDTO;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarInformationTests {
	
	@Autowired
	private CarInformationController carInformationController;
	private static final String VIN = "VSSZZZ6JZ9R056308";
	
	@Test
	public void testA1getEmptyByVin() {
		ResponseEntity<?> entity = carInformationController.readByVin(VIN);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
	}
	
	@Test
	public void testA2save() {
		CarInformationDTO carInformationDTO = new CarInformationDTO();
		carInformationDTO.setVin(VIN);
		carInformationDTO.setDate("2018-04-01");
		carInformationDTO.setDataProviderId(10);
		carInformationDTO.setOdometer(5600);
		String services[] = new String[] { "Air dam replaced", "Oil service"}; 
		carInformationDTO.setServiceDatails(Arrays.asList(services));
		ResponseEntity<?> entity = carInformationController.save(carInformationDTO);
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}
	
	@Test
	public void testA3saveBulk() {
		ResponseEntity<?> entity = carInformationController.saveByBulk(generateBulk());
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}
	
	@Test
	public void testA4GetByVin() {
		ResponseEntity<?> entity  = carInformationController.readByVin(VIN);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		CarInformationBulkDTO carInformationBulkDTO = (CarInformationBulkDTO) entity.getBody();
		List<CarInformationDTO> carInformationsDTO = carInformationBulkDTO.getCarInformations();
		assertEquals(5, carInformationsDTO.size());
		assertEquals(true, carInformationsDTO.get(3).getOdometerChanged());
	}
	
	private CarInformationBulkDTO generateBulk() {
		CarInformationBulkDTO caInformationBulkDTO = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			caInformationBulkDTO = mapper.readValue(CarInformationTests.class.getResourceAsStream("/car.json"), CarInformationBulkDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caInformationBulkDTO;
	}

}

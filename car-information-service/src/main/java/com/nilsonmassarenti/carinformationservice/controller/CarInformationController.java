package com.nilsonmassarenti.carinformationservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nilsonmassarenti.carinformationservice.model.CarInformation;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationBulkDTO;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationDTO;
import com.nilsonmassarenti.carinformationservice.service.CarInformationService;

@RestController
@RequestMapping("/v1/informations")
public class CarInformationController {

	@Autowired
	private CarInformationService carInformationService;
	
	@PostMapping("/")
	public ResponseEntity<?> save(@Valid @RequestBody CarInformationDTO carInformationDTO){
		CarInformation carInformation = carInformationService.convertCarInformationDTOToCarInformation(carInformationDTO);
		carInformation = carInformationService.save(carInformation);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<?> saveByBulk(@Valid @RequestBody CarInformationBulkDTO carInformationBulkDTO){
		List<CarInformation> carInformations = carInformationService.convertCarInformationBulkDTOToCarInformation(carInformationBulkDTO.getCarInformations());
		carInformationService.saveBulk(carInformations);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{vin}")
	public ResponseEntity<?> readByVin(@PathVariable String vin){
		List<CarInformation> carInformations = carInformationService.getInformationByVin(vin);
		if (carInformations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			CarInformationBulkDTO carInformationBulkDTO = carInformationService.convertCarInformationsToCarInformationBulkDTO(carInformations);
			return new ResponseEntity<>(carInformationBulkDTO, HttpStatus.OK);
		}
	} 
}

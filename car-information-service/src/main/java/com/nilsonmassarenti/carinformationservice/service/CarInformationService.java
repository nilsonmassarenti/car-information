package com.nilsonmassarenti.carinformationservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilsonmassarenti.carinformationservice.model.CarInformation;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationBulkDTO;
import com.nilsonmassarenti.carinformationservice.model.dto.CarInformationDTO;
import com.nilsonmassarenti.carinformationservice.repository.CarInformationRepository;

@Service
public class CarInformationService {
	
	@Autowired
	private CarInformationRepository carInformationRepository;
	
	public CarInformation save(CarInformation carInformation) {
		return carInformationRepository.save(carInformation);
		
	}
	
	public Boolean saveBulk(List<CarInformation> carInformations) {
		for (CarInformation carInformation : carInformations) {
			carInformationRepository.save(carInformation);
		}
		return true;
	}
	
	public List<CarInformation> getInformationByVin(String vin) {
		List<CarInformation> carInformations =  carInformationRepository.getById(vin);
		List<CarInformation> carInformationsUpdated = new ArrayList<>();
		if (!carInformations.isEmpty()) {
			carInformations.sort((c1, c2)-> c1.getDate().compareTo(c2.getDate()));
			Integer lastOdometer = 0;
			for (CarInformation carInformation : carInformations) {
				if (carInformation.getOdometer() < lastOdometer) {
					carInformation.setOdometerChanged(true);
				}
				lastOdometer = carInformation.getOdometer();
				carInformationsUpdated.add(carInformation);
			}
		}
		return carInformations;
	}
	
	public CarInformation convertCarInformationDTOToCarInformation(CarInformationDTO carInformationDTO) {
		CarInformation carInformation = new CarInformation(carInformationDTO.getVin(), LocalDate.parse(carInformationDTO.getDate()), carInformationDTO.getDataProviderId(), carInformationDTO.getOdometer(), carInformationDTO.getServiceDatails());
		return carInformation;
	}
	
	public List<CarInformation> convertCarInformationBulkDTOToCarInformation(List<CarInformationDTO> carInformationsDTO) {
		List<CarInformation> carInformations = new ArrayList<>();
		for (CarInformationDTO carInformationDTO : carInformationsDTO) {
			carInformations.add(convertCarInformationDTOToCarInformation(carInformationDTO));
		}
		return carInformations;
	}
	
	public CarInformationBulkDTO convertCarInformationsToCarInformationBulkDTO(List<CarInformation> carInformations) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<CarInformationDTO> carInformationsDTO = new ArrayList<>();
		for (CarInformation carInformation : carInformations) {
			CarInformationDTO carInformationDTO = new CarInformationDTO();
			carInformationDTO.setVin(carInformation.getVin());
			carInformationDTO.setDate(carInformation.getDate().format(formatter));
			carInformationDTO.setDataProviderId(carInformation.getDataProviderId());
			carInformationDTO.setOdometer(carInformation.getOdometer());
			carInformationDTO.setServiceDatails(carInformation.getServiceDatails());
			carInformationDTO.setOdometerChanged(carInformation.getOdometerChanged());
			carInformationsDTO.add(carInformationDTO);
		}
		CarInformationBulkDTO carInformationBulkDTO = new CarInformationBulkDTO();
		carInformationBulkDTO.setCarInformations(carInformationsDTO);
		return carInformationBulkDTO;
	}

}

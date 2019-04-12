package com.nilsonmassarenti.carinformationservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nilsonmassarenti.carinformationservice.model.CarInformation;

@Component
public class CarInformationRepository {
	private List<CarInformation> carsInformation;

	public CarInformationRepository() {
		carsInformation = new ArrayList<>();
	}

	public CarInformation save(CarInformation carInformation) {
		this.carsInformation.add(carInformation);
		return carInformation;
	}

	public List<CarInformation> getById(String vin) {
		return this.carsInformation.stream().filter(carInformation -> vin.equals(carInformation.getVin()))
				.collect(Collectors.toList());
	}
}

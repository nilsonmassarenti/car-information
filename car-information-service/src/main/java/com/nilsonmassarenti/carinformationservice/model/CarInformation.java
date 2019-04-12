package com.nilsonmassarenti.carinformationservice.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CarInformation {
	@Getter 
	private String vin;
	@Getter 
	private LocalDate date;
	@Getter
	private Integer dataProviderId;
	@Getter
	private Integer odometer;
	@Getter @Setter
	private Boolean odometerChanged;
	@Getter
	private List<String> serviceDatails;
	public CarInformation(String vin, LocalDate date, Integer dataProviderId, Integer odometer,
			List<String> serviceDatails) {
		this.vin = vin;
		this.date = date;
		this.dataProviderId = dataProviderId;
		this.odometer = odometer;
		this.serviceDatails = serviceDatails;
	}
	
	
	
}

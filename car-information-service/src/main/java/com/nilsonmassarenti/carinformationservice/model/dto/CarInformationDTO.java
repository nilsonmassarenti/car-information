package com.nilsonmassarenti.carinformationservice.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class CarInformationDTO {

	@NotNull
	@JsonProperty("vin")
	@Getter
	@Setter
	private String vin;

	@NotNull
	@JsonProperty("date")
	@Getter
	@Setter
	private String date;

	@NotNull
	@JsonProperty("data_provider_id")
	@Getter
	@Setter
	private Integer dataProviderId;

	@NotNull
	@JsonProperty("odometer_reading")
	@Getter
	@Setter
	private Integer odometer;

	@JsonProperty("odometer_changed")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Getter
	@Setter
	private Boolean odometerChanged;

	@JsonProperty("service_details")
	@Getter
	@Setter
	private List<String> serviceDatails;
}

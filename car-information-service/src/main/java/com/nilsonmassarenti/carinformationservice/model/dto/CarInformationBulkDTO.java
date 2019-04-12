package com.nilsonmassarenti.carinformationservice.model.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class CarInformationBulkDTO {
	
	@NotEmpty
	@Valid
	@JsonProperty("records")
	@Getter @Setter
	private List<CarInformationDTO> carInformations;
}

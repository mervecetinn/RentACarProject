package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {

	@NotNull
	private int id;

	@NotNull
	@Size(min = 3, max = 20)
	private String name;
}
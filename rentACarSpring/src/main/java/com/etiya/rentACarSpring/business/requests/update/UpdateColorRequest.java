package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	
	@NotNull
	private int id;

	@NotNull
	@Size(min=3,max=15)
	private String name;
}
package com.etiya.rentACarSpring.business.requests.create;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@NotNull
	private Date maintenanceStartDate;

	@NotNull
	private Date maintenanceFinishDate;

	@NotNull
	private int carId;
}

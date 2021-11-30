package com.etiya.rentACarSpring.business.requests.update;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	@NotNull
	private int id;
	
	@NotNull
    private Date maintenanceStartDate;
	
	@NotNull
	private Date maintenanceFinishDate;
	
	@NotNull
	private int carId;
}

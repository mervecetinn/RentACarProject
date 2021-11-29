package com.etiya.rentACarSpring.business.requests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	private Date maintenanceStartDate;
	
	private Date maintenanceFinishDate;
	
	private int carId;
}

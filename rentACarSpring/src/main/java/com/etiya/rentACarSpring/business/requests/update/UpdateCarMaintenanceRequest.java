package com.etiya.rentACarSpring.business.requests.update;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	private int id;
	
    private Date maintenanceStartDate;
	
	private Date maintenanceFinishDate;
	
	private int carId;
}

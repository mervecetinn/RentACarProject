package com.etiya.rentACarSpring.business.requests.update;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	@NotNull
	@Min(value = 1,message ="Lütfen bir bakım id girin")
	private int id;
	
	@NotNull
    private Date maintenanceStartDate;
	
	@NotNull
	private Date maintenanceFinishDate;
	
	@NotNull
	@JsonIgnore
	private int carId;
}

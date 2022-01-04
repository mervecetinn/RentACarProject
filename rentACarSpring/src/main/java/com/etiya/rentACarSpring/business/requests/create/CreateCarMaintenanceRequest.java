package com.etiya.rentACarSpring.business.requests.create;

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
public class CreateCarMaintenanceRequest {

	@JsonIgnore
	private int id;

	@NotNull
	private Date maintenanceStartDate;

	private Date maintenanceFinishDate;

	@NotNull
	@Min(value = 1)
	private int carId;
}

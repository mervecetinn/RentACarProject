package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceSearchListDto {

    private int id;

    private Date maintenanceStartDate;

    private Date maintenanceFinishDate;

    private int carId;
}

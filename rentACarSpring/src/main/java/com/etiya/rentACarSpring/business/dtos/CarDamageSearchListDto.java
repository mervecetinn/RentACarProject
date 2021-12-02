package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageSearchListDto {

    private int id;

    private String damageInformation;

    private int carId;
}

package com.etiya.rentACarSpring.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarDamageRequest {

    @Min(value = 1,message = "Lütfen bir id giriniz.")
    private int id;
}

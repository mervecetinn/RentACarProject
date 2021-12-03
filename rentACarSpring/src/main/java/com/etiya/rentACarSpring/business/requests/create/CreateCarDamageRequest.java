package com.etiya.rentACarSpring.business.requests.create;

import com.etiya.rentACarSpring.entities.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
    @JsonIgnore
    private int id;

    @NotNull
    @Size(min=10,max=200)
    private String damageInformation;

    @NotNull
    @Min(value = 1,message = "Lütfen car id girin.")
    private int carId;


}

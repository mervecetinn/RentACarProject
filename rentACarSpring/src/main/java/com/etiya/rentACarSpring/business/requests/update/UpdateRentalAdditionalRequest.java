package com.etiya.rentACarSpring.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalAdditionalRequest {

    @NotNull
    @Min(value = 1,message ="Lütfen bir rental additional id girin")
    private int id;

    @NotNull
    @Min(value = 1,message ="Lütfen bir item id girin")
    private int additionalItemId;

    @NotNull
    @Min(value = 1,message ="Lütfen bir rental id girin")
    private int rentalId;
}

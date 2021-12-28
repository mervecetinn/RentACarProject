package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAdditionalSearchListDto {
    private int id;
    private int additionalItemId;
    private int rentalId;
}

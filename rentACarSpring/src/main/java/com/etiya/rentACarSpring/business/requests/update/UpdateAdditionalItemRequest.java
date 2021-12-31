package com.etiya.rentACarSpring.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalItemRequest {

    @Min(value = 1,message ="Lütfen bir ek hizmet id girin")
    private int id;

    @NotNull
    @NotBlank
    @Size(min=2,max=50)
    private String name;

    @NotNull
    @Range(min = 1,max = 1000000)
    private double dailyPrice;
}

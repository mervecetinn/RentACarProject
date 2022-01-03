package com.etiya.rentACarSpring.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
    @JsonIgnore
    private int id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=300)
    private String damageInformation;

    @NotNull
    @Min(value = 1,message = "Lütfen car id girin.")
    private int carId;


}

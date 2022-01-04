package com.etiya.rentACarSpring.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {

    @Min(value = 1)
    private int id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=300)
    private String damageInformation;

}

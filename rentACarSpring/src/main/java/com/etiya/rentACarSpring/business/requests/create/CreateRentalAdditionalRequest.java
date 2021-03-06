package com.etiya.rentACarSpring.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalAdditionalRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @Min(value = 1)
    private int additionalItemId;

    @NotNull
    @Min(value = 1)
    private int rentalId;
}

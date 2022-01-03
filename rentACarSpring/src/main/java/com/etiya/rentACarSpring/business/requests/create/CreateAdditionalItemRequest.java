package com.etiya.rentACarSpring.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalItemRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @NotBlank
    @Size(min=2,max=50)
    private String name;

    @NotNull
    @Range(min = 1,max = 1000000)
    private double dailyPrice;
}

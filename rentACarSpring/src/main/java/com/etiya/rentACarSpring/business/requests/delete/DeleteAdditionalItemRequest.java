package com.etiya.rentACarSpring.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalItemRequest {

    @NotNull
    @Min(value = 1)
    private int id;
}

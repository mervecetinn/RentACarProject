package com.etiya.rentACarSpring.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLanguageRequest {

    @NotNull
    @Size(min = 2,max=30)
    private String languageName;
}

package com.etiya.rentACarSpring.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @Size(min = 2,max=144)
    private String messageContent;

    @NotNull
    @Min(value = 1,message = "Lütfen geçerli bir id girin.")
    private int messageKeyId;

    @NotNull
    @Min(value = 1,message = "Lütfen geçerli bir id girin.")
    private int languageId;
}

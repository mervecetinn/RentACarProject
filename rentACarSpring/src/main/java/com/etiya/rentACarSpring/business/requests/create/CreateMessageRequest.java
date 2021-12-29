package com.etiya.rentACarSpring.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {

    @JsonIgnore
    private int id;
    private String messageContent;

    @NotNull
    @Range(min = 1,message = "lütfen geçerli bir id girin.")
    private int messageKeyId;

    @NotNull
    @Range(min = 1,message = "lütfen geçerli bir id girin.")
    private int languageId;
}

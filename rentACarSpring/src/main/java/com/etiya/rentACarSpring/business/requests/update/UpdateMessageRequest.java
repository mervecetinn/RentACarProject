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
public class UpdateMessageRequest {
    @NotNull
    @Min(value = 1,message = "Lütfen geçerli bir id girin")
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 2,max=144)
    private String messageContent;

    @NotNull
    @Min(value = 1,message ="Lütfen bir message key id girin.")
    private int messageKeyId;

    @NotNull
    @Min(value = 1,message ="Lütfen bir language id girin.")
    private int languageId;
}

package com.etiya.rentACarSpring.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageRequest {
    @NotNull
    @Min(value = 1)
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 2,max=144)
    private String messageContent;

    @NotNull
    @Min(value = 1)
    private int messageKeyId;

    @NotNull
    @Min(value = 1)
    private int languageId;
}

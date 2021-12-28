package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSearchListDto {
    private int id;
    private String messageContent;
    private int languageId;
    private int messageKeyId;
}

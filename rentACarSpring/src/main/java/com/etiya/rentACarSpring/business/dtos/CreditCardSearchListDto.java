package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardSearchListDto {
    private int creditCardId;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
    private int userId;

}

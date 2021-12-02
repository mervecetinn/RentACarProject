package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInvoiceDetail {
    private int userId;
    private String invoiceNumber;
    private double invoiceAmount;
    private int countOfRentalDays;
    private int carId;
}


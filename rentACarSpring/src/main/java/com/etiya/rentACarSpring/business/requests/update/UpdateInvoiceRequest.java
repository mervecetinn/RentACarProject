package com.etiya.rentACarSpring.business.requests.update;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	private int invoiceNumber;
	
    private Date creationDate;
	
	private Date startDateOfRental;

	private Date finishDateOfRental;
	
	private int countOfRentalDays;
	
	private double invoiceAmount;
	
	private int userId;
}

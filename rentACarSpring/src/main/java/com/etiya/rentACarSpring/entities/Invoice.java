package com.etiya.rentACarSpring.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	
	@Column(name="count_of_rental_days")
	private int countOfRentalDays;
	
	@Column(name="invoice_amount")
	private double invoiceAmount;
	
	@OneToOne
	@JoinColumn(name="rental_id")
	private Rental rental;

}

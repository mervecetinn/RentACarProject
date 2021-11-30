package com.etiya.rentACarSpring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Date creationDate;
	
	@Column(name="sta_date")
	private Date startDateOfRental;
	
	@Column(name="rent_date")
	private Date finishDateOfRental;
	
	@Column(name="count_of_rental_days")
	private int countOfRentalDays;
	
	@Column(name="invoice_amount")
	private double invoiceAmount;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private ApplicationUser applicationUser;

}

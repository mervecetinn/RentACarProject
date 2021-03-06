package com.etiya.rentACarSpring.entities;

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
@Table(name = "credit_cards")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int creditCardId;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_holder_name")
	private String cardHolderName;
	
	@Column(name="expirationDate")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private ApplicationUser applicationUser;

}

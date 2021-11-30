package com.etiya.rentACarSpring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.etiya.rentACarSpring.core.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "users")
public class ApplicationUser extends User {
	
    @OneToOne(mappedBy = "applicationUser")
	private IndividualCustomer individualCustomer;
    
    @OneToOne(mappedBy = "applicationUser")
	private CorporateCustomer corporateCustomer;
    
    @OneToMany(mappedBy = "applicationUser")
    private List<Rental> rentals;
    
    @OneToMany(mappedBy = "applicationUser")
    private List<CreditCard> creditcards;
}

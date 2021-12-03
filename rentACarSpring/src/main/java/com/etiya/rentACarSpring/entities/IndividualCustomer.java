package com.etiya.rentACarSpring.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="individual_customers")
public class IndividualCustomer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "individual_customer_id")
	private int individualCustomerId;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    @Column(name="birthday")
    private LocalDate birthday;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;

   
    
   

    

}



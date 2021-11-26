package com.etiya.rentACarSpring.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import javax.persistence.ForeignKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true,exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="individual_customers")
public class IndividualCustomer extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    @Column(name="birthday")
    private LocalDateTime birthday;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name="USER_IND_FK"),name = "user_id",nullable = false)
    private User user;

   
    
   

    

}



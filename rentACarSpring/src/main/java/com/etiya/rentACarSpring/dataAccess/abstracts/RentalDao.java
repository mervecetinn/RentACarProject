package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	Rental getByCarIdAndReturnDateIsNull(int carId);
	

}

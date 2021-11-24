package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	List<Rental> getByCarId(int carId);
}

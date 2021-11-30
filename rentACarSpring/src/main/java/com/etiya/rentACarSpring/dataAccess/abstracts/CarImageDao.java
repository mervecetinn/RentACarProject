package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACarSpring.entities.CarImage;
import com.etiya.rentACarSpring.entities.complexTypes.CarImageDetail;

public interface CarImageDao extends JpaRepository<CarImage, Integer> {

	List<CarImage> getByCarId(int carId);
	boolean existsById(int id);
	
}

package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Brand;


public interface BrandDao extends JpaRepository<Brand, Integer> {
	
 Brand getByName(String brandName);
 boolean existsById(int id);
 boolean existsByName(String brandName);
 
 
	
	

}

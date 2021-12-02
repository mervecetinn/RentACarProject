package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Brand;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BrandDao extends JpaRepository<Brand, Integer> {

 boolean existsById(int id);


 
 
	
	

}

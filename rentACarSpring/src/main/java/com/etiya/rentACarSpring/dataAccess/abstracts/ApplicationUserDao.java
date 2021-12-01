package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etiya.rentACarSpring.entities.ApplicationUser;

public interface ApplicationUserDao extends JpaRepository<ApplicationUser, Integer> {

	ApplicationUser getByEmail(String email);
	boolean existsByUserId(int id);
	
}

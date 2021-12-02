package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.RentalAdditional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAdditionalDao extends JpaRepository<RentalAdditional,Integer> {
}

package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.AdditionalItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalItemDao extends JpaRepository<AdditionalItem,Integer> {
}

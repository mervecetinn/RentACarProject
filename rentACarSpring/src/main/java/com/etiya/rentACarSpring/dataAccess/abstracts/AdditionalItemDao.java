package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.AdditionalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalItemDao extends JpaRepository<AdditionalItem,Integer> {

    List<AdditionalItem> getByRentalAdditionalsIsNotNull();


}

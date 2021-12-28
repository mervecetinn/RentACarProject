package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.AdditionalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalItemDao extends JpaRepository<AdditionalItem,Integer> {

    /*@Query(value = "select distinct * from additional_items ai \n" +
            "inner join rental_additionals ra on ai.id=ra.additional_item_id",nativeQuery = true)
    List<AdditionalItem> getAdditionalItemsInRentalAdditional();*/

    List<AdditionalItem> getByRentalAdditionalsIsNotNull();


}

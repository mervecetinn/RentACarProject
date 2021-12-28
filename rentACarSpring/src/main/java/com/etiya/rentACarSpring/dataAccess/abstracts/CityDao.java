package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityDao extends JpaRepository<City, Integer> {
    boolean existsById(int id);

    @Query(value = "select distinct ci.id,ci.name from cities ci \n" +
            "inner join cars ca on ci.id=ca.city_id",nativeQuery = true)
    List<City> getCitiesRelatedWithCars();

}

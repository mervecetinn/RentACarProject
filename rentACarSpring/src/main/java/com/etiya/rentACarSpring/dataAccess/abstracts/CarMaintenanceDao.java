package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etiya.rentACarSpring.entities.CarMaintenance;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer>{

    boolean existsByCarIdAndMaintenanceFinishDateIsNull(int carId);

    @Query(value="Select c.id from Cars c left join \n" +
            "(Select * from rentals r  where return_date is null) a on c.id=a.car_id \n" +
            "where a.rental_id is not null and c.id=?1",nativeQuery = true)
    List<Integer> getCarIfItIsOnRent(int id);

}

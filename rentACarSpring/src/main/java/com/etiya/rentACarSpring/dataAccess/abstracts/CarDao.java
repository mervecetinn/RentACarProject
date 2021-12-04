package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;

public interface CarDao extends JpaRepository<Car, Integer> {

	boolean existsById(int id);
	List<Car> getByCityId(int cityId);
	List<Car> getByBrandId(int brandId);
	List<Car> getByColorId(int colorId);
	List<Car> getByModelYear(int modelYear);


	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.kilometer,c.dailyPrice,c.description,cImg.image) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Left Join c.carImages cImg")
	List<CarDetail> getCarsWithDetails();
	
	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.kilometer,c.dailyPrice,c.description,cImg.image) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Left Join c.carImages cImg where c.id=:carId")
	List<CarDetail> getOneCarWithDetails(int carId);
	
	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.kilometer,c.dailyPrice,c.description) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl")
	List<CarDetail> getCarsWithBrandAndColorDetails();

	
	@Query("Select new com.etiya.rentACarSpring.business.dtos.CarSearchListDto(c.id,c.modelYear,c.kilometer,c.dailyPrice,c.description) From Car c Inner Join c.brand b  where lower(b.name) =lower(:brandName) ")
	List<CarSearchListDto> getByBrandName(String brandName);

	
	@Query(value="Select * from Cars c left join \r\n"
			+ "(Select * from rentals where return_date is null) a on c.id=a.car_id where rental_id is not null and c.id=?1",nativeQuery = true)
    List<Car> getCarIfItIsOnRent(int id);
	
	@Query(value="Select * from Cars c left join \r\n"
			+ "(Select * from car_maintenances where maintenance_finish_date is null) a on c.id=a.car_id where a.id is not null and c.id=?1",nativeQuery = true)
	List<Car> getCarIfItIsOnMaintenance(int id);
	
	@Query(value="select distinct(b.id) from cars c full join brands b on c.brand_id=b.id where b.id=?1",nativeQuery = true)
	Object getOneBrandId(int brandId);
	
	@Query(value="select distinct(co.id) from cars c full join colors co on c.color_id=co.id where co.id=?1",nativeQuery = true)
	Object getOneColorId(int colorId);
	
	@Query(value="select * from cars c left join   (select * from car_maintenances m  where m.maintenance_finish_date is null) a\n" +
			"on c.id=a.car_id where a.id  is null",nativeQuery = true)
	List<Car> findAllCarsWhichIsNotOnMaintenance();
	
	
	
	
}
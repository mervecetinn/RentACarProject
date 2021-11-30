package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;

public interface CarDao extends JpaRepository<Car, Integer> {

	
	
	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.dailyPrice,c.description,cImg.image) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Full Join c.carImages cImg")
	List<CarDetail> getCarsWithDetails();
	
	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.dailyPrice,c.description,cImg.image) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Full Join c.carImages cImg where c.id=:carId")
	List<CarDetail> getOneCarWithDetails(int carId);
	
	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail"
			+ "(c.id,b.name,cl.name,c.modelYear,c.dailyPrice,c.description) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl")
	List<CarDetail> getCarsWithBrandAndColorDetails();
	
	List<Car> getByModelYear(int modelYear);
	
	@Query("Select new com.etiya.rentACarSpring.business.dtos.CarSearchListDto(c.id,c.modelYear,c.dailyPrice,c.description) From Car c Inner Join c.brand b  where b.name=:brandName ")
	List<CarSearchListDto> getByBrandName(String brandName);
	
	List<Car> getByBrandId(int brandId);
	
	List<Car> getByColorId(int colorId);
	
	@Query("Select new com.etiya.rentACarSpring.business.dtos.CarSearchListDto(c.id,c.modelYear,c.dailyPrice,c.description) From Car c Left Join c.rentals r where r.returnDate=null and r.rentDate!=null and c.id=:id")
    DataResult<CarSearchListDto> getCarIfItIsOnRent(int id);
	
	@Query("Select new com.etiya.rentACarSpring.business.dtos.CarSearchListDto(c.id,c.modelYear,c.dailyPrice,c.description) From Car c Left Join c.carMaintenances cm where cm.maintenanceFinishDate is null and cm.maintenanceStartDate is not null and c.id=:id")
	DataResult<CarSearchListDto> getCarIfItIsOnMaintenance(int id);
	
	
	
	
}

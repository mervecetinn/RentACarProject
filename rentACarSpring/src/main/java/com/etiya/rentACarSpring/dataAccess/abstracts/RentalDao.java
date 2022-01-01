package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalDao extends JpaRepository<Rental, Integer> {

	boolean existsByCarIdAndReturnDateIsNull(int carId);

	List<Rental> getByRentalAdditionalsIsNotNull();

	@Query(value = "Select EXTRACT(DAY FROM MAX(return_date)-MIN(rent_date)) AS DateDifference From rentals where rental_id=?1",nativeQuery = true)
	Integer getDayBetweenDatesOfRental(int rentalId);

	@Query(value = "select daily_price from cars c inner join rentals r on c.id=r.car_id where rental_id=?1",nativeQuery = true)
	Integer getDailyPriceOfRentedCar(int rentalId);

	@Query(value = "SELECT ai.daily_price FROM Rentals r INNER JOIN rental_additionals ra ON r.rental_id=ra.rental_id inner join additional_items ai on ra.additional_item_id=ai.id where r.rental_id=?1 ",nativeQuery = true)
	List<Double> getAdditionalItemsOfRelevantRental(int rentalId);

	@Query(value = "select r.rent_date from  rentals r where rental_id=?1",nativeQuery = true)
	LocalDateTime getRentDateOfRental(int rentalId);

	@Query(value = "select r.taken_from_city_id from  rentals r where rental_id=?1",nativeQuery = true)
	List<Integer> getTakenFromCityId(int rentalId);






}

package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	DataResult<List<RentalSearchListDto>> getAll();
	DataResult<Integer> getDayBetweenDatesOfRental(int rentalId);
	DataResult<Integer> getDailyPriceOfRentedCar(int brandId);
	Result checkCarIsReturnedToSameCity(int rentalId);
	DataResult<Rental> getById(int rentalId);
	Double getAdditionalItemsTotalPrice(int rentalId);
}

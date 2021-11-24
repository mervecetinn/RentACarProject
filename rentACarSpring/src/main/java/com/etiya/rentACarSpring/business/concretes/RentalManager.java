package com.etiya.rentACarSpring.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalDao;
import com.etiya.rentACarSpring.entities.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Result result=BusinessRules.run(checkReturnDateExists(createRentalRequest.getCarId()));
		
		if(result!=null) {
			return result;
		}
		
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalDao.deleteById(deleteRentalRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<RentalSearchListDto>> getAll() {
		List<Rental> result = this.rentalDao.findAll();
		List<RentalSearchListDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalSearchListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentalSearchListDto>>(response) ;
	}
	
	private Result checkReturnDateExists(int carId) {
		List<Rental> rentals=this.rentalDao.getByCarId(carId);
		if(rentals!=null ) {
			for(Rental rental:rentals) {
			if(rental.getRentDate()!=null &&rental.getReturnDate()==null) {
				return new ErrorResult("Araba teslim edilmedi.");
			}
			}
		}
		
		return new SuccessResult("Araba kiralandı.");
		
	}

}

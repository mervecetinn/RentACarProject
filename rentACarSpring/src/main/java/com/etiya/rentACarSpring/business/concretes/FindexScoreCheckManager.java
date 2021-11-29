package com.etiya.rentACarSpring.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.FindexScoreCheckService;
import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.CreateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;

@Service
public class FindexScoreCheckManager implements FindexScoreCheckService {
	
	private CarService carService;
	private UserService userService;

	@Autowired
	public FindexScoreCheckManager(CarService carService, UserService userService) {
		this.carService = carService;
		this.userService = userService;
	}
	
	@Override
	public Result checkFindexScore(CreateRentalRequest createRentalRequest) {
		Result  result=BusinessRules.run(checkIfFindexScoreEnough(createRentalRequest.getCarId(),createRentalRequest.getUserId()));
		if(result!=null) {
			return result;
		}
		return new SuccessResult();
	}

	
	private Result checkIfFindexScoreEnough(int carId,int userId) {
		int carFindexScore=this.carService.getById(carId).getData().getMinFindexScore();
	    int individualCustomerFindexScore=this.userService.getByUserId(userId).getData().getIndividualCustomer().getFindexScore();
	    
	    if(individualCustomerFindexScore>=carFindexScore) {
	    	 return new SuccessResult();
	    }
	    return new ErrorResult("Your findex score is not enough for this car.");
	   
	}




	

}

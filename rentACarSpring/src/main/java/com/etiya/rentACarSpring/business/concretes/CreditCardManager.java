package com.etiya.rentACarSpring.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CreditCardDao;
import com.etiya.rentACarSpring.entities.ApplicationUser;
import com.etiya.rentACarSpring.entities.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
	
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	
    @Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService,
			UserService userService) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		Result result=BusinessRules.run(checkIfUserGiveConfirmationForSaveCreditCard());
		
		if(result!=null) {
			return result;
		}
		
		ApplicationUser user=this.userService.getByUserId(createCreditCardRequest.getUserId()).getData();
		
		CreditCard creditCard=this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		creditCard.setApplicationUser(user);
		this.creditCardDao.save(creditCard);
		return new SuccessResult("Credit card is added.");
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Result checkIfUserGiveConfirmationForSaveCreditCard() {
		return new SuccessResult();
	}

}

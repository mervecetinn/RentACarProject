package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.create.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCreditCardRequest;
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
		Result result=BusinessRules.run(checkIfUserNotExists(createCreditCardRequest.getUserId()));
		
		if(result!=null) {
			return result;
		}
		
		ApplicationUser user=this.userService.getByUserId(createCreditCardRequest.getUserId()).getData();
		
		CreditCard creditCard=this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		creditCard.setApplicationUser(user);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.DataAdded);
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		CreditCard creditCard=this.creditCardDao.getById(updateCreditCardRequest.getId());
		ApplicationUser user=creditCard.getApplicationUser();
		CreditCard updatedCreditCard=this.modelMapperService.forRequest().map(updateCreditCardRequest,CreditCard.class);
		updatedCreditCard.setApplicationUser(user);
		this.creditCardDao.save(updatedCreditCard);
		return new SuccessResult(Messages.DataUpdated);

	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		this.creditCardDao.deleteById(deleteCreditCardRequest.getId());
		return new SuccessResult(Messages.DataDeleted);
	}
	
	private Result checkIfUserNotExists(int userId){
		if(!this.userService.checkUserExists(userId).isSuccess()){
			return  new ErrorResult(Messages.UserIsNotFound);
		}
		return new SuccessResult();
	}

}

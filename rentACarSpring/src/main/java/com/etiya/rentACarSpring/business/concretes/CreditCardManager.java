package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.create.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CreditCardDao;
import com.etiya.rentACarSpring.entities.ApplicationUser;
import com.etiya.rentACarSpring.entities.CreditCard;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardManager implements CreditCardService {
	
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private MessageService messageService;
	
    @Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService,
			UserService userService,MessageService messageService) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
		this.messageService=messageService;
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		Result result=BusinessRules.run(checkIfUserNotExists(createCreditCardRequest.getUserId()),checkIfUserAlreadyHasThatCreditCard(createCreditCardRequest.getCardNumber(),createCreditCardRequest.getUserId()));
		
		if(result!=null) {
			return result;
		}
		
		ApplicationUser user=this.userService.getByUserId(createCreditCardRequest.getUserId()).getData();
		
		CreditCard creditCard=this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		creditCard.setApplicationUser(user);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(this.messageService.getMessage(Messages.CreditCardAdded));
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		Result result=BusinessRules.run(checkIfUserAlreadyHasThatCreditCard(updateCreditCardRequest.getCardNumber(),this.creditCardDao.getById(updateCreditCardRequest.getId()).getApplicationUser().getUserId()),
				checkIfCreditCardIsNotExists(updateCreditCardRequest.getId()));

		if(result!=null) {
			return result;
		}
		CreditCard creditCard=this.creditCardDao.getById(updateCreditCardRequest.getId());
		ApplicationUser user=creditCard.getApplicationUser();
		CreditCard updatedCreditCard=this.modelMapperService.forRequest().map(updateCreditCardRequest,CreditCard.class);
		updatedCreditCard.setApplicationUser(user);
		this.creditCardDao.save(updatedCreditCard);
		return new SuccessResult(this.messageService.getMessage(Messages.CreditCardUpdated));

	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		Result result=BusinessRules.run(checkIfCreditCardIsNotExists(deleteCreditCardRequest.getId()));

		if(result!=null) {
			return result;
		}
		this.creditCardDao.deleteById(deleteCreditCardRequest.getId());
		return new SuccessResult(this.messageService.getMessage(Messages.CreditCardDeleted));
	}

	@Override
	public DataResult<List<CreditCardSearchListDto>> getAll() {
		List<CreditCard> result=this.creditCardDao.findAll();
		List<CreditCardSearchListDto> response=result.stream()
				.map(creditCard -> this.modelMapperService.forDto().map(creditCard,CreditCardSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response);
	}

	private Result checkIfUserNotExists(int userId){
		if(!this.userService.checkUserExists(userId).isSuccess()){
			return  new ErrorResult(this.messageService.getMessage(Messages.UserNotFound));
		}
		return new SuccessResult();
	}

	private Result checkIfUserAlreadyHasThatCreditCard(String creditCardNumber, int userId){
		if (this.creditCardDao.existsCreditCardByCardNumberAndAndApplicationUserUserId(creditCardNumber, userId)){
			return new ErrorResult(this.messageService.getMessage(Messages.UserAlreadyHasThatCreditCard));
		}
		return new SuccessResult();
	}

	private Result checkIfCreditCardIsNotExists(int id) {
		if (!this.creditCardDao.existsById(id)) {
			return new ErrorResult(this.messageService.getMessage(Messages.CreditCardNotFound));

		}
		return new SuccessResult();

	}

}


package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.create.CreateCorporateCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.CorporateCustomerService;
import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCorporateCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.rentACarSpring.entities.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private MessageService messageService;

	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService,UserService userService,MessageService messageService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
		this.messageService=messageService;

	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result= BusinessRules.run(checkIfUserAlreadyExists(createCorporateCustomerRequest.getEmail()));
		if(result!=null){
			return result;
		}

		ApplicationUser user = new ApplicationUser();
		user.setEmail(createCorporateCustomerRequest.getEmail());
		user.setPassword(createCorporateCustomerRequest.getPassword());
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomer.setApplicationUser(user);
		this.userService.add(user);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(this.messageService.getMessage(Messages.CorporateCustomerAdded));
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		Result result= BusinessRules.run(checkIfCorporateCustomerIsNotExists(updateCorporateCustomerRequest.getCorporateCustomerId()),
				checkIfUserAlreadyExists(updateCorporateCustomerRequest.getEmail()));
		if(result!=null){
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(this.messageService.getMessage(Messages.CorporateCustomerUpdated));
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		Result result= BusinessRules.run(checkIfCorporateCustomerIsNotExists(deleteCorporateCustomerRequest.getCorporateCustomerId()));
		if(result!=null){
			return result;
		}
		CorporateCustomer corporateCustomer=this.corporateCustomerDao.getById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		ApplicationUser user=corporateCustomer.getApplicationUser();
		this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		this.userService.delete(user);
		return new SuccessResult(this.messageService.getMessage(Messages.CorporateCustomerDeleted));
	}

	@Override
	public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
		List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
		List<CorporateCustomerSearchListDto> response = result.stream().map(individualCustomer -> modelMapperService
				.forDto().map(individualCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response,this.messageService.getMessage(Messages.CorporateCustomersListed));
	}

	private Result checkIfCorporateCustomerIsNotExists(int id) {
		if (!this.corporateCustomerDao.existsById(id)) {
			return new ErrorResult(this.messageService.getMessage(Messages.CorporateCustomerNotFound));

		}
		return new SuccessResult();

	}

	private Result checkIfUserAlreadyExists(String email) {
		if(this.userService.getByEmail(email).isSuccess()) {
			return new ErrorResult(this.messageService.getMessage(Messages.UserAlreadyExists));
		}
		return new SuccessResult();
	}
	
	

}

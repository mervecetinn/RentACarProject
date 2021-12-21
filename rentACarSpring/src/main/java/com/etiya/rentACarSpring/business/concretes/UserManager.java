package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.rentACarSpring.entities.ApplicationUser;

@Service
public class UserManager implements UserService {

	private ApplicationUserDao applicationUserDao;
	private ModelMapperService modelMapperService;
	private MessageService messageService;
	
	@Autowired
	public UserManager(ApplicationUserDao applicationUserDao,ModelMapperService modelMapperService,MessageService messageService) {
		this.applicationUserDao = applicationUserDao;
		this.modelMapperService=modelMapperService;
		this.messageService=messageService;
	}

	@Override
	public Result add(ApplicationUser user) {

		this.applicationUserDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result update(ApplicationUser user) {

		this.applicationUserDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result delete(ApplicationUser user) {
		this.applicationUserDao.deleteById(user.getUserId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<ApplicationUser> result = this.applicationUserDao.findAll();
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<UserSearchListDto>>(response,this.messageService.getMessage(Messages.UsersListed)) ;
	}

	@Override
	public DataResult<ApplicationUser> getByEmail(String email) {
		ApplicationUser user=this.applicationUserDao.getByEmail(email);
		if(user==null) {
			return new ErrorDataResult<ApplicationUser>(null);
		}
		return new SuccessDataResult<ApplicationUser>(user);
		
	}

	@Override
	public DataResult<ApplicationUser> getByUserId(int id) {
		ApplicationUser user=this.applicationUserDao.getById(id);
		return new SuccessDataResult<ApplicationUser>(user);
	}

	@Override
	public Result checkUserExists(int userId) {
		if(this.applicationUserDao.existsByUserId(userId)){
			return new SuccessResult();
		}
		return new ErrorResult();
	}


}

package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.rentACarSpring.entities.ApplicationUser;


@Service
public class UserManager implements UserService {

	private ApplicationUserDao applicationUserDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public UserManager(ApplicationUserDao applicationUserDao,ModelMapperService modelMapperService) {
		this.applicationUserDao = applicationUserDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		ApplicationUser user = modelMapperService.forRequest().map(createUserRequest, ApplicationUser.class);
		this.applicationUserDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		ApplicationUser user = modelMapperService.forRequest().map(updateUserRequest, ApplicationUser.class);
		this.applicationUserDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		this.applicationUserDao.deleteById(deleteUserRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<ApplicationUser> result = this.applicationUserDao.findAll();
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<UserSearchListDto>>(response) ;
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

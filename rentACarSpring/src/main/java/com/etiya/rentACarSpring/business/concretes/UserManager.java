package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.UserDao;
import com.etiya.rentACarSpring.entities.User;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public UserManager(UserDao userDao,ModelMapperService modelMapperService) {
		this.userDao = userDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		this.userDao.deleteById(deleteUserRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<User> result = this.userDao.findAll();
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<UserSearchListDto>>(response) ;
	}

}

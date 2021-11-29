package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.ApplicationUser;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	DataResult<List<UserSearchListDto>> getAll();
	DataResult<ApplicationUser> getByEmail(String email);
	DataResult<ApplicationUser> getByUserId(int userId);
}

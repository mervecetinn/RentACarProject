package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.ApplicationUser;

public interface UserService {
	Result add(ApplicationUser user);
	Result update(ApplicationUser user);
	Result delete(ApplicationUser user);
	DataResult<List<UserSearchListDto>> getAll();
	DataResult<ApplicationUser> getByEmail(String email);
	DataResult<ApplicationUser> getByUserId(int userId);
    Result checkUserExists(int userId);
}

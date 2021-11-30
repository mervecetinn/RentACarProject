package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.requests.create.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
//	
//	@PostMapping("add")
//	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
//		return this.userService.add(createUserRequest);
//	}
//	
//	@PutMapping("update")
//	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
//		return this.userService.update(updateUserRequest);
//	}
//	
//	@DeleteMapping("delete")
//	public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
//		return this.userService.delete(deleteUserRequest);
//	}
	
	@GetMapping("all")
	public Result getAll() {
		
		return this.userService.getAll();
	}
	
	
}

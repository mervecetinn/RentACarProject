package com.etiya.rentACarSpring.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("all")
	public Result getAll() {
		return this.userService.getAll();
	}

}

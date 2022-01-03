package com.etiya.rentACarSpring.business.requests.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
 
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@Size(min=8,max=20)
	private  String password;
}

package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	@NotNull
	@Min(value = 1,message ="Lütfen bir user id girin")
	private int id;
	
	@NotNull
	@Email(message = "Email düzeni hatalı")
	private String email;

	@NotNull
	@NotBlank
	@Size(min=8,max=20)
	private String password;
}

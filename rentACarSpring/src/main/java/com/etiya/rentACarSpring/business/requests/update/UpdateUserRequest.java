package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(min=8,max=20)
	private String password;
}

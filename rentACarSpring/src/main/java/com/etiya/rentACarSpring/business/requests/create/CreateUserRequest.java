package com.etiya.rentACarSpring.business.requests.create;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	
	@NotNull
    private String email;

	@NotNull
	@Size(min=8,max=20)
    private String password;

}

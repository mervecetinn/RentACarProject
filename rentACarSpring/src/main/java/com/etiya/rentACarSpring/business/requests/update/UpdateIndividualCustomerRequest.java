package com.etiya.rentACarSpring.business.requests.update;

import java.time.LocalDate;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

	@NotNull
	@Min(value = 1)
	private int individualCustomerId;
	
	@NotNull
	@JsonIgnore
	private int userId;
	
	@NotNull
	@Email
	private String email;

	@NotNull
	@NotBlank
	@Size(min=8,max=20)
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min=2,max=20)
	private String firstName;

	@NotNull
	@NotBlank
	@Size(min=2,max=20)
	private String lastName;

	@NotNull
	@Past
	private LocalDate birthday;
}

package com.etiya.rentACarSpring.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListDto {
	private int userId;

	private String email;

    private String password;
}

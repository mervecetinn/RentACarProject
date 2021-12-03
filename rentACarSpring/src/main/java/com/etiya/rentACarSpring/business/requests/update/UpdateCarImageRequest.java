package com.etiya.rentACarSpring.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	
	@NotNull
	@Min(value = 1,message ="Lütfen bir resim id girin")
	private int id;
	
	@NotNull
	private MultipartFile imageFile;

}

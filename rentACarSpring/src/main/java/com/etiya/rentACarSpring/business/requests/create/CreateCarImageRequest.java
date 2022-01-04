package com.etiya.rentACarSpring.business.requests.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImageRequest {
	
	@NotNull
	@Min(value = 1)
	private int carId;

	@NotNull
	private MultipartFile imageFile;
	
}

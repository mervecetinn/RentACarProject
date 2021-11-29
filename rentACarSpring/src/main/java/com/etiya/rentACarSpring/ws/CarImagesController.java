package com.etiya.rentACarSpring.ws;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.etiya.rentACarSpring.business.abstracts.CarImageService;
import com.etiya.rentACarSpring.business.requests.CreateCarImageRequest;
import com.etiya.rentACarSpring.business.requests.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;


@RestController
@RequestMapping("api/carImages")
public class CarImagesController {
	
	private CarImageService carImageService;

	@Autowired
	public CarImagesController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	
	
	@PostMapping("add")
    public Result add(@RequestParam("carId") int carId,@RequestParam("image") MultipartFile file) throws IOException {
		CreateCarImageRequest createCarImageRequest=new CreateCarImageRequest(carId,file);
        return this.carImageService.add(createCarImageRequest);
    }
	
	@PutMapping("update")
	public Result update(@RequestParam("imageId") int imageId,@RequestParam("image") MultipartFile file) throws IOException {
		UpdateCarImageRequest updateCarImageRequest=new UpdateCarImageRequest(imageId,file);
		return this.carImageService.update(updateCarImageRequest);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestBody DeleteCarImageRequest deleteCarImageRequest) {
		return this.carImageService.delete(deleteCarImageRequest);
	}
	
	@GetMapping("all")
	public Result getAll() {
		return this.carImageService.getAll();
	}
	
	

}

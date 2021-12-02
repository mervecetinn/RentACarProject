package com.etiya.rentACarSpring.entities.complexTypes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {

	
		private int id;
		
	    
		private String brandName;
		
	    
		private String colorName;
		
	  
		private int modelYear;


	    private int kilometer;
	    
	   
	   	private double dailyPrice;
		
	   
		private String description;
		
		
		private byte[] image;


		public CarDetail(int id, String brandName, String colorName, int modelYear, int kilometer, double dailyPrice,
				String description) {
			super();
			this.id = id;
			this.brandName = brandName;
			this.colorName = colorName;
			this.modelYear = modelYear;
			this.kilometer=kilometer;
			this.dailyPrice = dailyPrice;
			this.description = description;
		}


			
		
	    
	    
}

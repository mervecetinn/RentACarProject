package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.requests.create.CreateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/creditCards")
public class CreditCardsController {
	
	private CreditCardService creditCardService;
	
	@Autowired
	public CreditCardsController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}


	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest ) {
		return this.creditCardService.add(createCreditCardRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);


	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}

}

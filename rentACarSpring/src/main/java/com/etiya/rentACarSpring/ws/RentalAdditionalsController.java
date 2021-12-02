package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.RentalAdditionalService;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.RentalAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/rentalAdditionals")
public class RentalAdditionalsController {

    private RentalAdditionalService rentalAdditionalService;

    @Autowired
    public RentalAdditionalsController(RentalAdditionalService rentalAdditionalService){
        this.rentalAdditionalService=rentalAdditionalService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateRentalAdditionalRequest createRentalAdditionalRequest){
        return this.rentalAdditionalService.add(createRentalAdditionalRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateRentalAdditionalRequest updateRentalAdditionalRequest){
        return this.rentalAdditionalService.update(updateRentalAdditionalRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteRentalAdditionalRequest deleteRentalAdditionalRequest){
        return this.rentalAdditionalService.delete(deleteRentalAdditionalRequest);
    }


}

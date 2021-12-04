package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.CorporateCustomerService;
import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateCorporateCustomerRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/corporateCustomers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;

    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        super();
        this.corporateCustomerService=corporateCustomerService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return this.corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("update")
    public Result add(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        return this.corporateCustomerService.update(updateCorporateCustomerRequest);
    }

    @DeleteMapping("delete")
    public Result add(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }

    @GetMapping("all")
    public DataResult<List<CorporateCustomerSearchListDto>> getAll(){
        return this.corporateCustomerService.getAll();
    }


}

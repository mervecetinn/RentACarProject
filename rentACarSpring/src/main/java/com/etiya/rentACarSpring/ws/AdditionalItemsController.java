package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.AdditionalItemService;
import com.etiya.rentACarSpring.business.dtos.AdditionalItemSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateAdditionalItemRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.AdditionalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalItems")
public class AdditionalItemsController {
    private AdditionalItemService additionalItemService;

    @Autowired
    public AdditionalItemsController(AdditionalItemService additionalItemService){
        this.additionalItemService=additionalItemService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateAdditionalItemRequest createAdditionalItemRequest){
        return this.additionalItemService.add(createAdditionalItemRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateAdditionalItemRequest updateAdditionalItemRequest){
        return this.additionalItemService.update(updateAdditionalItemRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalItemRequest deleteAdditionalItemRequest){
        return this.additionalItemService.delete(deleteAdditionalItemRequest);
    }

    @GetMapping("all")
    public DataResult<List<AdditionalItemSearchListDto>> getAll(){
        return this.additionalItemService.getAll();
    }
}

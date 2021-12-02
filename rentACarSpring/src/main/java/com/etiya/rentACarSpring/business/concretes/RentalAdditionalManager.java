package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalItemService;
import com.etiya.rentACarSpring.business.abstracts.RentalAdditionalService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalAdditionalDao;
import com.etiya.rentACarSpring.entities.RentalAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalAdditionalManager implements RentalAdditionalService {
    private RentalAdditionalDao rentalAdditionalDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalItemService additionalItemService;

    @Autowired
    public RentalAdditionalManager(RentalAdditionalDao rentalAdditionalDao,ModelMapperService modelMapperService,RentalService rentalService,AdditionalItemService additionalItemService){
        this.rentalAdditionalDao=rentalAdditionalDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalItemService=additionalItemService;
    }



    @Override
    public Result add(CreateRentalAdditionalRequest createRentalAdditionalRequest) {
        RentalAdditional rentalAdditional=this.modelMapperService.forRequest().map(createRentalAdditionalRequest,RentalAdditional.class);
        this.rentalAdditionalDao.save(rentalAdditional);

        return new SuccessResult();
    }

    @Override
    public Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest) {
        RentalAdditional rentalAdditional=this.rentalAdditionalDao.getById(updateRentalAdditionalRequest.getId());
        rentalAdditional.setRental(this.rentalService.getById(updateRentalAdditionalRequest.getRentalId()).getData());
        rentalAdditional.setAdditionalItem(this.additionalItemService.getById(updateRentalAdditionalRequest.getAdditionalItemId()).getData());
        this.rentalAdditionalDao.save(rentalAdditional);

        return  new SuccessResult();
    }

    @Override
    public Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest) {
        this.rentalAdditionalDao.deleteById(deleteRentalAdditionalRequest.getId());
        return new SuccessResult();
    }
}

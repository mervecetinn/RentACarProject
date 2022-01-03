package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalItemService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.abstracts.RentalAdditionalService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.RentalAdditionalSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalAdditionalDao;
import com.etiya.rentACarSpring.entities.RentalAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalAdditionalManager implements RentalAdditionalService {
    private RentalAdditionalDao rentalAdditionalDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalItemService additionalItemService;
    private MessageService messageService;

    @Autowired
    public RentalAdditionalManager(RentalAdditionalDao rentalAdditionalDao,ModelMapperService modelMapperService,
                                   RentalService rentalService,AdditionalItemService additionalItemService,MessageService messageService){
        this.rentalAdditionalDao=rentalAdditionalDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalItemService=additionalItemService;
        this.messageService=messageService;
    }


    @Override
    public Result add(CreateRentalAdditionalRequest createRentalAdditionalRequest) {
        Result result = BusinessRules.run(checkIfAdditionalItemIsNotExists(createRentalAdditionalRequest.getAdditionalItemId()),
                checkIfRentalIsNotExists(createRentalAdditionalRequest.getRentalId()));

        if (result != null) {
            return result;
        }
        RentalAdditional rentalAdditional=this.modelMapperService.forRequest().map(createRentalAdditionalRequest,RentalAdditional.class);
        this.rentalAdditionalDao.save(rentalAdditional);

        return new SuccessResult(this.messageService.getMessage(Messages.RentalAdditionalAdded));
    }

    @Override
    public Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest) {
        Result result = BusinessRules.run(checkIfRentalAdditionalIsNotExists(updateRentalAdditionalRequest.getId()),
                checkIfAdditionalItemIsNotExists(updateRentalAdditionalRequest.getAdditionalItemId()),
                checkIfRentalIsNotExists(updateRentalAdditionalRequest.getRentalId()));

        if (result != null) {
            return result;
        }
        RentalAdditional rentalAdditional=this.rentalAdditionalDao.getById(updateRentalAdditionalRequest.getId());
        rentalAdditional.setRental(this.rentalService.getById(updateRentalAdditionalRequest.getRentalId()).getData());
        rentalAdditional.setAdditionalItem(this.additionalItemService.getById(updateRentalAdditionalRequest.getAdditionalItemId()).getData());
        this.rentalAdditionalDao.save(rentalAdditional);

        return  new SuccessResult(this.messageService.getMessage(Messages.RentalAdditionalUpdated));
    }

    @Override
    public Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest) {
        Result result = BusinessRules.run(checkIfRentalAdditionalIsNotExists(deleteRentalAdditionalRequest.getId()));

        if (result != null) {
            return result;
        }
        this.rentalAdditionalDao.deleteById(deleteRentalAdditionalRequest.getId());
        return new SuccessResult(this.messageService.getMessage(Messages.RentalAdditionalDeleted));
    }

    @Override
    public DataResult<List<RentalAdditionalSearchListDto>> getAll() {
        List<RentalAdditional> result=this.rentalAdditionalDao.findAll();
        List<RentalAdditionalSearchListDto> response=result.stream()
                .map(rentalAdditional -> this.modelMapperService.forDto().map(rentalAdditional,RentalAdditionalSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }
    private Result checkIfRentalAdditionalIsNotExists(int id) {
        if (!this.rentalAdditionalDao.existsById(id)) {
            return new ErrorResult(this.messageService.getMessage(Messages.RentalAdditionalNotFound));

        }
        return new SuccessResult();

    }
    private Result checkIfAdditionalItemIsNotExists(int id){
        if(!this.additionalItemService.getById(id).isSuccess()){
            return new ErrorResult(this.messageService.getMessage(Messages.AdditionalItemNotFound));
        }
        return new SuccessResult();
    }
    private Result checkIfRentalIsNotExists(int id){
        if(!this.rentalService.getById(id).isSuccess()){
            return new ErrorResult(this.messageService.getMessage(Messages.RentalNotFound));
        }
        return new SuccessResult();
    }
}

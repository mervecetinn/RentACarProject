package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalItemService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.AdditionalItemSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateAdditionalItemRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.AdditionalItemDao;
import com.etiya.rentACarSpring.entities.AdditionalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalItemManager implements AdditionalItemService {
    private AdditionalItemDao additionalItemDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Autowired
    public AdditionalItemManager(AdditionalItemDao additionalItemDao,ModelMapperService modelMapperService, MessageService messageService){
        this.additionalItemDao=additionalItemDao;
        this.modelMapperService=modelMapperService;
        this.messageService=messageService;
    }


    @Override
    public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
        Result result= BusinessRules.run(checkIfAdditionalItemIsAlreadyExists(createAdditionalItemRequest.getName()));

        if(result!=null){
            return result;
        }

        AdditionalItem additionalItem=modelMapperService.forRequest().map(createAdditionalItemRequest,AdditionalItem.class);
        this.additionalItemDao.save(additionalItem);
        return new SuccessResult(this.messageService.getMessage(Messages.AdditionalItemAdded));
    }

    @Override
    public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
        Result result= BusinessRules.run(checkIfAdditionalItemIsAlreadyExists(updateAdditionalItemRequest.getName()),
                checkIfAdditionalItemIsNotExists(updateAdditionalItemRequest.getId()));

        if(result!=null){
            return result;
        }
        AdditionalItem additionalItem=this.additionalItemDao.getById(updateAdditionalItemRequest.getId());
        additionalItem.setDailyPrice(updateAdditionalItemRequest.getDailyPrice());
        additionalItem.setName(updateAdditionalItemRequest.getName());
        this.additionalItemDao.save(additionalItem);

        return new SuccessResult(this.messageService.getMessage(Messages.AdditionalItemUpdated));
    }

    @Override
    public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
        Result result = BusinessRules.run(checkIfAdditionalItemHasNotAnyRentalAdditional(deleteAdditionalItemRequest.getId()),
                checkIfAdditionalItemIsNotExists(deleteAdditionalItemRequest.getId()));
        if (result != null) {
            return result;
        }
        this.additionalItemDao.deleteById(deleteAdditionalItemRequest.getId());
        return new SuccessResult(this.messageService.getMessage(Messages.AdditionalItemDeleted));
    }

    @Override
    public DataResult<List<AdditionalItemSearchListDto>> getAll() {
        List<AdditionalItem> result=this.additionalItemDao.findAll();
        List<AdditionalItemSearchListDto> response=result.stream()
                .map(additionalItem -> modelMapperService.forDto().map(additionalItem,AdditionalItemSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalItemSearchListDto>>(response,this.messageService.getMessage(Messages.AdditionalItemsListed));
    }

    @Override
    public DataResult<AdditionalItem> getById(int id) {
        if(!this.additionalItemDao.existsById(id)){
            return new ErrorDataResult<>(null);
        }
        return new SuccessDataResult<AdditionalItem>(this.additionalItemDao.getById(id));
    }

    private Result checkIfAdditionalItemIsAlreadyExists(String itemName){
        List<AdditionalItem> items=this.additionalItemDao.findAll();
        for(AdditionalItem item:items){
            if(item.getName().equalsIgnoreCase(itemName.trim())){
                return new ErrorResult(this.messageService.getMessage(Messages.AdditionalItemAlreadyExists));
            }
        }
        return new SuccessResult();
    }

    private Result checkIfAdditionalItemHasNotAnyRentalAdditional(int additionalItemId){
       List<AdditionalItem> result=this.additionalItemDao.getByRentalAdditionalsIsNotNull();
        for(AdditionalItem additionalItem:result){
            if(additionalItem.getId()==additionalItemId){
                return new ErrorResult(this.messageService.getMessage(Messages.AdditionalItemCanNotDelete));
            }
        }
        return new SuccessResult();
        }

    public Result checkIfAdditionalItemIsNotExists(int id) {
        if (!this.additionalItemDao.existsById(id)) {
            return new ErrorResult(this.messageService.getMessage(Messages.AdditionalItemNotFound));

        }
        return new SuccessResult();

    }

    }



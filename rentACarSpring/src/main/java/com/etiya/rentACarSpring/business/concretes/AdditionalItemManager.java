package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalItemService;
import com.etiya.rentACarSpring.business.dtos.AdditionalItemSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteAdditionalItemRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateAdditionalItemRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
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

    @Autowired
    public AdditionalItemManager(AdditionalItemDao additionalItemDao,ModelMapperService modelMapperService){
        this.additionalItemDao=additionalItemDao;
        this.modelMapperService=modelMapperService;
    }


    @Override
    public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
        AdditionalItem additionalItem=modelMapperService.forRequest().map(createAdditionalItemRequest,AdditionalItem.class);
        this.additionalItemDao.save(additionalItem);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
        AdditionalItem additionalItem=this.additionalItemDao.getById(updateAdditionalItemRequest.getId());
        additionalItem.setDailyPrice(updateAdditionalItemRequest.getDailyPrice());
        additionalItem.setName(updateAdditionalItemRequest.getName());
        this.additionalItemDao.save(additionalItem);

        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
        this.additionalItemDao.deleteById(deleteAdditionalItemRequest.getId());
        return new SuccessResult();
    }

    @Override
    public DataResult<List<AdditionalItemSearchListDto>> getAll() {
        List<AdditionalItem> result=this.additionalItemDao.findAll();
        List<AdditionalItemSearchListDto> response=result.stream()
                .map(additionalItem -> modelMapperService.forDto().map(additionalItem,AdditionalItemSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalItemSearchListDto>>(response);
    }

    @Override
    public DataResult<AdditionalItem> getById(int id) {
        return new SuccessDataResult<AdditionalItem>(this.additionalItemDao.getById(id));
    }
}

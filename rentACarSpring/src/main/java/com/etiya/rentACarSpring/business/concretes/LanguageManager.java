package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.LanguageService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.LanguageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateLanguageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteLanguageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.LanguageDao;
import com.etiya.rentACarSpring.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageManager implements LanguageService {
    private LanguageDao languageDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;
    @Autowired
    public LanguageManager(LanguageDao languageDao,ModelMapperService modelMapperService,MessageService messageService) {
        this.languageDao = languageDao;
        this.modelMapperService=modelMapperService;
        this.messageService=messageService;
    }


    @Override
    public Result add(CreateLanguageRequest createLanguageRequest) {
        Result result = BusinessRules.run(checkIfLanguageIsAlreadyExists(createLanguageRequest.getLanguageName()));

        if (result != null) {
            return result;
        }
        Language language=this.modelMapperService.forRequest().map(createLanguageRequest,Language.class);
        this.languageDao.save(language);
        return new SuccessResult(this.messageService.getMessage(Messages.LanguageAdded));
    }

    @Override
    public Result update(UpdateLanguageRequest updateLanguageRequest) {
        Result result = BusinessRules.run(checkIfLanguageIsAlreadyExists(updateLanguageRequest.getLanguageName()),
                checkIfLanguageIsNotExists(updateLanguageRequest.getLanguageId()));

        if (result != null) {
            return result;
        }
        Language language=this.modelMapperService.forRequest().map(updateLanguageRequest,Language.class);
        this.languageDao.save(language);
        return new SuccessResult(this.messageService.getMessage(Messages.LanguageUpdated));
    }

    @Override
    public Result delete(DeleteLanguageRequest deleteLanguageRequest) {
        Result result= BusinessRules.run(checkIfLanguageHasAnyMessage(deleteLanguageRequest.getLanguageId()),
                checkIfLanguageIsNotExists(deleteLanguageRequest.getLanguageId()));
        if(result!=null){
            return result;
        }
       this.languageDao.deleteById(deleteLanguageRequest.getLanguageId());
        return new SuccessResult(this.messageService.getMessage(Messages.LanguageDeleted));
    }

    @Override
    public DataResult<List<LanguageSearchListDto>> getAll() {
       List<Language> result=this.languageDao.findAll();
       List<LanguageSearchListDto> response=result.stream()
               .map(language->this.modelMapperService.forDto().map(language,LanguageSearchListDto.class))
               .collect(Collectors.toList());

       return new SuccessDataResult<List<LanguageSearchListDto>>(response,this.messageService.getMessage(Messages.LanguagesListed));
    }

    @Override
    public Result checkIfLanguageIdExists(int languageId) {
        if(this.languageDao.existsById(languageId)==true){
            return new SuccessResult();
        }
        return new ErrorResult();

    }

    private Result checkIfLanguageHasAnyMessage(int languageId){
        List<Language> result=this.languageDao.getByMessagesIsNotNull();
        for(Language language:result){
            if(languageId==language.getLanguageId()){
                return new ErrorResult(this.messageService.getMessage(Messages.LanguageCanNotDelete));
            }
        }
        return new SuccessResult();
    }

    private Result checkIfLanguageIsAlreadyExists(String languageName){
        List<Language> languages=this.languageDao.findAll();
        for(Language language:languages){
            if(language.getName().equalsIgnoreCase(languageName.trim())){
                return new ErrorResult(this.messageService.getMessage(Messages.LanguageAlreadyExists));
            }
        }
        return new SuccessResult();
    }

    private Result checkIfLanguageIsNotExists(int id) {
        if (!this.languageDao.existsById(id)) {
            return new ErrorResult(this.messageService.getMessage(Messages.LanguageNotFound));

        }
        return new SuccessResult();

    }
}

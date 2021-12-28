package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.MessageKeyService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.MessageKeySearchListDto;
import com.etiya.rentACarSpring.business.dtos.MessageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageKeyRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.MessageKeyDao;
import com.etiya.rentACarSpring.entities.Brand;
import com.etiya.rentACarSpring.entities.Language;
import com.etiya.rentACarSpring.entities.MessageKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageKeyManager implements MessageKeyService {
    private MessageKeyDao messageKeyDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Autowired
    public MessageKeyManager(MessageKeyDao messageKeyDao,ModelMapperService modelMapperService,MessageService messageService) {
        this.messageKeyDao = messageKeyDao;
        this.modelMapperService=modelMapperService;
        this.messageService=messageService;
    }


    @Override
    public Result add(CreateMessageKeyRequest createMessageKeyRequest) {
        Result result = BusinessRules.run(checkIfMessageKeyAlreadyExists(createMessageKeyRequest.getMessageKey()));

        if (result != null) {
            return result;
        }
      MessageKey messageKey=this.modelMapperService.forRequest().map(createMessageKeyRequest,MessageKey.class);
      this.messageKeyDao.save(messageKey);
      return new SuccessResult(this.messageService.getMessage(Messages.MessageKeyAdded));
    }

    @Override
    public Result update(UpdateMessageKeyRequest updateMessageKeyRequest) {
        MessageKey messageKey=this.modelMapperService.forRequest().map(updateMessageKeyRequest,MessageKey.class);
        this.messageKeyDao.save(messageKey);
        return new SuccessResult(this.messageService.getMessage(Messages.MessageKeyUpdated));
    }

    @Override
    public Result delete(DeleteMessageKeyRequest deleteMessageKeyRequest) {
        Result result= BusinessRules.run(checkIfMessageKeyHasAnyMessage(deleteMessageKeyRequest.getMessageKeyId()));
        if(result!=null){
            return result;
        }
       this.messageKeyDao.deleteById(deleteMessageKeyRequest.getMessageKeyId());
       return new SuccessResult(this.messageService.getMessage(Messages.MessageKeyDeleted));

    }

    @Override
    public DataResult<List<MessageKeySearchListDto>> getAll() {
        List<MessageKey> result=this.messageKeyDao.findAll();
        List<MessageKeySearchListDto> response=result.stream()
                .map(messageKey -> this.modelMapperService.forDto().map(messageKey,MessageKeySearchListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<MessageKeySearchListDto>>(response,this.messageService.getMessage(Messages.MessageKeysListed));

    }

    private Result checkIfMessageKeyAlreadyExists(String messageKey) {
        List<MessageKey> messageKeys=this.messageKeyDao.findAll();
        for(MessageKey key:messageKeys){
            if(key.getMessageKey().equalsIgnoreCase(messageKey.toLowerCase())){
                return new ErrorResult(this.messageService.getMessage(Messages.MessageKeyAlreadyExists));
            }
        }
        return new SuccessResult();
    }

    private Result checkIfMessageKeyHasAnyMessage(int messageKeyId){
        List<MessageKey> result=this.messageKeyDao.getByMessagesIsNotNull();
        for(MessageKey messageKey:result){
            if(messageKeyId==messageKey.getMessageKeyId()){
                return new ErrorResult(this.messageService.getMessage(Messages.MessageKeyCanNotDelete));
            }
        }
        return new SuccessResult();
    }
}

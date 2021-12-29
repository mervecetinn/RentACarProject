package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.LanguageService;
import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.MessageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.MessageDao;
import com.etiya.rentACarSpring.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageManager implements MessageService {
    @Value("${message.languageId}")
    private Integer languageId;
    @Value("${message.defaultLanguageId}")
    private Integer defaultLanguageId;
    private MessageDao messageDao;
    private ModelMapperService modelMapperService;



    @Autowired
    public MessageManager(MessageDao messageDao,ModelMapperService modelMapperService) {

        this.messageDao = messageDao;
        this.modelMapperService=modelMapperService;




    }

    @Override
    public Result add(CreateMessageRequest createMessageRequest) {
        Result result = BusinessRules.run(checkIfSameMessageKeyIdAndLanguageIdExistsTogether(createMessageRequest.getLanguageId(),createMessageRequest.getMessageKeyId()));

        if (result != null) {
            return result;
        }
        Message message=this.modelMapperService.forRequest().map(createMessageRequest,Message.class);
        this.messageDao.save(message);
        return new SuccessResult(this.getMessage(Messages.MessageAdded));
    }

    @Override
    public Result update(UpdateMessageRequest updateMessageRequest) {
        Message message=this.modelMapperService.forRequest().map(updateMessageRequest,Message.class);
        this.messageDao.save(message);
        return new  SuccessResult(this.getMessage(Messages.MessageUpdated));
    }

    @Override
    public Result delete(DeleteMessageRequest deleteMessageRequest) {
        this.messageDao.deleteById(deleteMessageRequest.getId());
        return new SuccessResult(this.getMessage(Messages.MessageDeleted));
    }

    @Override
    public DataResult<List<MessageSearchListDto>> getAll() {
        List<Message> result= this.messageDao.findAll();
        List<MessageSearchListDto> messages = result.stream()
                .map(message -> this.modelMapperService.forDto().map(message,MessageSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(messages,this.getMessage(Messages.MessagesListed));
    }

    @Override
    public String getMessage(String messageKey) {
        giveDefaultLanguage();
        String messageContent=this.messageDao.getMessageByLanguageIdAndKey(this.languageId,messageKey);
        if(messageContent!=null) {
            return messageContent;
        }
        return messageKey;

    }

    private void giveDefaultLanguage(){
        if(!this.messageDao.existsByLanguage_LanguageId(this.languageId)){
            this.languageId=this.defaultLanguageId;
        }
    }

    private Result checkIfSameMessageKeyIdAndLanguageIdExistsTogether(int languageId,int messageKeyId){
        List<Message> result= this.messageDao.findAll();
        if(!result.stream().anyMatch(m->m.getLanguage().getLanguageId()==languageId &&m.getMessageKey().getMessageKeyId()==messageKeyId)){
            return new SuccessResult();
        }
        return new ErrorResult(this.getMessage(Messages.MessageKeyIdAndLanguageIdCanNotRepeatTogether));
    }



}

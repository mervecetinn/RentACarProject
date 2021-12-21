package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.MessageDao;
import com.etiya.rentACarSpring.entities.Message;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class MessageManager implements MessageService {
    @Value("${message.languageId}")
    private Integer languageId;
    private MessageDao messageDao;
    private ModelMapperService modelMapperService;


    @Autowired
    public MessageManager(MessageDao messageDao,ModelMapperService modelMapperService) {

        this.messageDao = messageDao;
        this.modelMapperService=modelMapperService;

    }


    @Override
    public Result add(CreateMessageRequest createMessageRequest) {
        Message message=this.modelMapperService.forRequest().map(createMessageRequest,Message.class);
        this.messageDao.save(message);
        return new SuccessResult();
    }


    private List<Message> getAllMessagesByLanguageId() {
        List<Message> messageList=this.messageDao.findMessagesByLanguageIdEquals(this.languageId);
        return messageList;
    }

    private Map<String,String> createMessageMap(){

        Map<String,String> messageMap=new HashMap<>();
        for(Message message:getAllMessagesByLanguageId()){
            messageMap.put(message.getMessageName(),message.getMessageContent());
        }
        return messageMap;
    }

    @Override
    public  String getMessage(String messageName) {
       String messageContent=createMessageMap().get(messageName);
       return messageContent;

    }

}

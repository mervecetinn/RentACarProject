package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessagesController {
    private MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }



    @PostMapping("add")
    public Result add(@RequestBody CreateMessageRequest createMessageRequest){
       return this.messageService.add(createMessageRequest);
    }
}

package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.dtos.MessageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessagesController {
    private MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }



    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateMessageRequest createMessageRequest){
        return this.messageService.add(createMessageRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateMessageRequest updateMessageRequest){
        return this.messageService.update(updateMessageRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteMessageRequest deleteMessageRequest){
        return this.messageService.delete(deleteMessageRequest);
    }

    @GetMapping("all")
    public DataResult<List<MessageSearchListDto>> getAll(){
        return this.messageService.getAll();
    }
}

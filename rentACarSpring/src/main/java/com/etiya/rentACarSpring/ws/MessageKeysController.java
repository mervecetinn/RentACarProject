package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.MessageKeyService;
import com.etiya.rentACarSpring.business.dtos.MessageKeySearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageKeyRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/messageKeys")
public class MessageKeysController {
    private MessageKeyService messageKeyService;

    @Autowired
    public MessageKeysController(MessageKeyService messageKeyService) {
        this.messageKeyService = messageKeyService;
    }



    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateMessageKeyRequest createMessageKeyRequest){
        return this.messageKeyService.add(createMessageKeyRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateMessageKeyRequest updateMessageKeyRequest){
        return this.messageKeyService.update(updateMessageKeyRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteMessageKeyRequest deleteMessageKeyRequest){
        return this.messageKeyService.delete(deleteMessageKeyRequest);
    }

    @GetMapping("all")
    public DataResult<List<MessageKeySearchListDto>> getAll(){
        return this.messageKeyService.getAll();
    }

}

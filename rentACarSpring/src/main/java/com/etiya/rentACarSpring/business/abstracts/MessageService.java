package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.MessageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    Result add(CreateMessageRequest createMessageRequest);

    Result update(UpdateMessageRequest updateMessageRequest);

    Result delete(DeleteMessageRequest deleteMessageRequest);

    DataResult<List<MessageSearchListDto>> getAll();

    String getMessage(String messageKey);

}

package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.MessageKeySearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageKeyRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Language;
import com.etiya.rentACarSpring.entities.MessageKey;

import java.util.List;

public interface MessageKeyService {
    Result add(CreateMessageKeyRequest createMessageKeyRequest);
    Result update(UpdateMessageKeyRequest updateMessageKeyRequest);
    Result delete(DeleteMessageKeyRequest deleteMessageKeyRequest);
    DataResult<List<MessageKeySearchListDto>> getAll();
}

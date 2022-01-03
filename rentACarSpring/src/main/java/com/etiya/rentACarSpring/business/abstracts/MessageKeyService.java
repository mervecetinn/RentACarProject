package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.MessageKeySearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteMessageKeyRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateMessageKeyRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import java.util.List;

public interface MessageKeyService {
    Result add(CreateMessageKeyRequest createMessageKeyRequest);
    Result update(UpdateMessageKeyRequest updateMessageKeyRequest);
    Result delete(DeleteMessageKeyRequest deleteMessageKeyRequest);
    DataResult<List<MessageKeySearchListDto>> getAll();
}

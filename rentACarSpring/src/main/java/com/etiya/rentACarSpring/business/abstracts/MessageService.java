package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    Result add(CreateMessageRequest createMessageRequest);
    String getMessage(String messageName);

}

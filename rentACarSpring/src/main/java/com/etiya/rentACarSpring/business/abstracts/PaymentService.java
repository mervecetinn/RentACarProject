package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.payment.PayCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface PaymentService {
    Result payByCreditCard(PayCreditCardRequest payCreditCardRequest);
}

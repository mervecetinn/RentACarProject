package com.etiya.rentACarSpring.core.adapters;

import com.etiya.rentACarSpring.business.abstracts.PaymentService;
import com.etiya.rentACarSpring.business.requests.payment.PayCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.outServices.FakePosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosServiceAdapter implements PaymentService {
    private FakePosService fakePosService;

    @Autowired
    public PosServiceAdapter(FakePosService fakePosService){
        this.fakePosService=fakePosService;
    }
    @Override
    public Result payByCreditCard(PayCreditCardRequest payCreditCardRequest) {
        return this.fakePosService.pay(payCreditCardRequest);
    }
}

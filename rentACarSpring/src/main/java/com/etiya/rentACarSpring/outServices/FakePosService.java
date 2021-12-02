package com.etiya.rentACarSpring.outServices;

import com.etiya.rentACarSpring.business.requests.payment.PayCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class FakePosService {

    public Result pay(PayCreditCardRequest payCreditCardRequest){
        Result result= BusinessRules.run(checkIfLimitIsInsufficient(payCreditCardRequest));

        if(result!=null){
            return result;
        }
        return new SuccessResult();
    }

    private Result checkIfLimitIsInsufficient(PayCreditCardRequest payCreditCardRequest){
        double cardLimit=5000;
        if(payCreditCardRequest.getTotalPrice()>cardLimit){
            return new ErrorResult("Limit is insufficient!");
        }
        return new SuccessResult();
    }
}

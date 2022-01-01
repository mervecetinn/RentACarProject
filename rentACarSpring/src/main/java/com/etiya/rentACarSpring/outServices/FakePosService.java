package com.etiya.rentACarSpring.outServices;

import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class FakePosService {

    public Result pay(double totalPrice){
        Result result= BusinessRules.run(checkIfLimitIsInsufficient(totalPrice));

        if(result!=null){
            return result;
        }
        return new SuccessResult();
    }

    private Result checkIfLimitIsInsufficient(double totalPrice){
        double cardLimit=5000;
        if(totalPrice>cardLimit){
            return new ErrorResult();
        }
        return new SuccessResult();
    }
}

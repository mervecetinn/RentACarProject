package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface PaymentService {
    Result payByCreditCard(double totalPrice);
}

package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.CreateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;


public interface FindexScoreCheckService {

	Result checkFindexScore(CreateRentalRequest createRentalRequest);
}

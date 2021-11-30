package com.etiya.rentACarSpring.core.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CustomerFindexScoreService;
import com.etiya.rentACarSpring.outServices.FindexScoreService;

@Service
public class FindexScoreServiceAdapter implements CustomerFindexScoreService {

	private FindexScoreService findexScoreService;
	
	@Autowired
	public FindexScoreServiceAdapter(FindexScoreService findexScoreService) {
		super();
		this.findexScoreService = findexScoreService;
	}

	@Override
	public int getFindexScoreOfIndividualCustomer() {
		return this.findexScoreService.getFindexScoreOfIndividualCustomer();
	}

	@Override
	public int getFindexScoreOfCorporateCustomer() {
		return this.findexScoreService.getFindexScoreOfCorporateCustomer();
	}


}

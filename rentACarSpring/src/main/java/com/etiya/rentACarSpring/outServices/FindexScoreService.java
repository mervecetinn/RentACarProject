package com.etiya.rentACarSpring.outServices;

import org.springframework.stereotype.Service;

@Service
public class FindexScoreService {

	public int getFindexScoreOfIndividualCustomer() {

		return (int) (Math.random() * 1900);
	}

	public int getFindexScoreOfCorporateCustomer() {

		return (int) (Math.random() * 1900);
	}
}

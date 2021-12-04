package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.payment.PayCreditCardRequest;
import com.etiya.rentACarSpring.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.entities.concretes.User;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalDao;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerFindexScoreService customerFindexScoreService;
	private UserService userService;
	private CarService carService;
	private CityService cityService;
	private PaymentService paymentService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, UserService userService,
			CustomerFindexScoreService customerFindexScoreService, CarService carService,CityService cityService,
						 PaymentService paymentService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
		this.customerFindexScoreService = customerFindexScoreService;
		this.carService = carService;
		this.cityService=cityService;
		this.paymentService=paymentService;

	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		PayCreditCardRequest payCreditCardRequest=new PayCreditCardRequest();
		Result result = BusinessRules.run(checkReturnDateExists(createRentalRequest.getCarId()),
				checkCustomerFindexScoreIsEnough(createRentalRequest.getUserId(),createRentalRequest.getCarId()),
				checkCarIsNotOnMaintenance(createRentalRequest.getCarId()),checkIfCarIsNotExists(createRentalRequest.getCarId()),checkIfUserNotExists(createRentalRequest.getUserId()),checkIfLimitIsInsufficient(payCreditCardRequest));

		if (result != null) {
			return result;
		}

		ApplicationUser user = this.userService.getByUserId(createRentalRequest.getUserId()).getData();
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setApplicationUser(user);
		rental.setTakenFromCityId(this.carService.getById(createRentalRequest.getCarId()).getData().getCity().getId());
		rental.setTakenKilometer(carService.getById(createRentalRequest.getCarId()).getData().getKilometer());
		this.rentalDao.save(rental);
		if (createRentalRequest.getReturnKilometer() > 0 && this.carService.getById(createRentalRequest.getCarId()).getData().getKilometer()<createRentalRequest.getReturnKilometer()){
			this.carService.updateCarKilometer(createRentalRequest.getCarId(),createRentalRequest.getReturnKilometer());
		}

		return new SuccessResult(Messages.RentedIsSuccessful);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result = BusinessRules.run();

		if (result != null) {
			return result;
		}

		ApplicationUser user;
		Car car;
		Rental rental=this.rentalDao.getById(updateRentalRequest.getRentalId());
		user=rental.getApplicationUser();
		car=rental.getCar();
		if (updateRentalRequest.getReturnKilometer() > 0 && this.carService.getById(car.getId()).getData().getKilometer()<updateRentalRequest.getReturnKilometer()){
			this.carService.updateCarKilometer(car.getId(),updateRentalRequest.getReturnKilometer());

		}
		//rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setApplicationUser(user);
		rental.setCar(car);
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setReturnToCityId(updateRentalRequest.getReturnToCityId());
		rental.setReturnKilometer(updateRentalRequest.getReturnKilometer());
		City city=this.cityService.getById(updateRentalRequest.getReturnToCityId()).getData();
		car.setCity(city);
		this.rentalDao.save(rental);

		return new SuccessResult(Messages.DataUpdated);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalDao.deleteById(deleteRentalRequest.getId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<RentalSearchListDto>> getAll() {
		List<Rental> result = this.rentalDao.findAll();
		List<RentalSearchListDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<RentalSearchListDto>>(response);
	}

	@Override
	public DataResult<Integer> getDayBetweenDatesOfRental(int rentalId) {
		return new SuccessDataResult<>(this.rentalDao.getDayBetweenDatesOfRental(rentalId)) ;
	}

	@Override
	public DataResult<Integer> getDailyPriceOfRentedCar(int brandId) {
		return new SuccessDataResult<>(this.rentalDao.getDailyPriceOfRentedCar(brandId));
	}

	@Override
	public Result checkCarIsReturnedToSameCity(int rentalId) {
		Rental rental=this.rentalDao.getById(rentalId);
		if(rental.getTakenFromCityId()==rental.getReturnToCityId()){
			return new SuccessResult();
		}
		return new ErrorResult();
	}

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(rentalId));
	}

	@Override
	public Double getAdditionalItemsTotalPrice(int rentalId){
		List<Double> items=this.rentalDao.getAdditionalItemsOfRelevantRental(rentalId);
		double additionalsTotalPrice=0;

		for(Double item:items){
			additionalsTotalPrice+=item;
		}
		return additionalsTotalPrice;
	}

	private Result checkReturnDateExists(int carId) {
		Rental rental = this.rentalDao.getByCarIdAndReturnDateIsNull(carId);
		if (rental != null) {
			return new ErrorResult(Messages.CarIsOnRent);
		}
		return new SuccessResult();

	}

	private Result checkCustomerFindexScoreIsEnough(int userId, int carId) {
		int carFindexScore = this.carService.getById(carId).getData().getMinFindexScore();
		int customerFindexScore = 0;
		ApplicationUser user = this.userService.getByUserId(userId).getData();
		if (user == null) {
			return new ErrorResult();
		}

		if (user.getIndividualCustomer() != null) {
			customerFindexScore = this.customerFindexScoreService.getFindexScoreOfIndividualCustomer();
		}
		if (user.getCorporateCustomer() != null) {
			customerFindexScore = this.customerFindexScoreService.getFindexScoreOfCorporateCustomer();
		}

		if (customerFindexScore >= carFindexScore) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.FindexScoreIsNotEnough);
	}


	private Result checkCarIsNotOnMaintenance(int carId) {
		if (!this.carService.checkCarIsNotOnMaintenance(carId).isSuccess()) {
			return new ErrorResult(Messages.CarIsOnMaintenance);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIsNotExists(int carId) {
		if(!this.carService.checkCarExists(carId).isSuccess()) {
			return new ErrorResult(Messages.CarIsNotFound);
		}
		
		return new SuccessResult();
	}

	private Result checkIfUserNotExists(int userId){
		if(!this.userService.checkUserExists(userId).isSuccess()){
			return new  ErrorResult(Messages.UserIsNotFound);
		}
		return  new SuccessResult();
	}

	private Result checkIfLimitIsInsufficient(PayCreditCardRequest payCreditCardRequest){
		if(!this.paymentService.payByCreditCard(payCreditCardRequest).isSuccess()){
			return new ErrorResult(Messages.LimitIsInsufficient);
		}
		return new SuccessResult();
	}


}

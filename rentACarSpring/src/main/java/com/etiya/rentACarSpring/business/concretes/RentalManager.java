package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.requests.payment.PayCreditCardRequest;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.City;
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
import com.etiya.rentACarSpring.entities.ApplicationUser;
import com.etiya.rentACarSpring.entities.Rental;

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
			CustomerFindexScoreService customerFindexScoreService, CarService carService,CityService cityService,PaymentService paymentService) {
		super();
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
				checkCustomerFindexScoreIsEnough(createRentalRequest.getUserId(), createRentalRequest.getCarId()),
				checkCarIsNotOnMaintenance(createRentalRequest.getCarId()),checkIfCarIsNotExists(createRentalRequest.getCarId()),checkIfUserNotExists(createRentalRequest.getUserId()),checkIfLimitIsInsufficient(payCreditCardRequest));

		if (result != null) {
			return result;
		}

		ApplicationUser user = this.userService.getByUserId(createRentalRequest.getUserId()).getData();
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setApplicationUser(user);
		rental.setTakenFromCityId(this.carService.getById(createRentalRequest.getCarId()).getData().getCity().getId());
		this.rentalDao.save(rental);
		return new SuccessResult();
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
		//rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setApplicationUser(user);
		rental.setCar(car);
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setReturnToCityId(updateRentalRequest.getReturnToCityId());
		City city=this.cityService.getById(updateRentalRequest.getReturnToCityId()).getData();
		car.setCity(city);
		this.rentalDao.save(rental);

		return new SuccessResult();
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

	private Result checkReturnDateExists(int carId) {
		Rental rental = this.rentalDao.getByCarIdAndReturnDateIsNull(carId);
		if (rental != null) {
			return new ErrorResult("Araba teslim edilmedi.");
		}
		return new SuccessResult("Araba kiralandı");

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
		return new ErrorResult("Your findex score is not enough for this car!" + "your findex score: "
				+ customerFindexScore + " car findex score: " + carFindexScore);
	}

	private Result checkCarIsNotOnMaintenance(int carId) {
		if (!this.carService.checkCarIsNotOnMaintenance(carId).isSuccess()) {
			return new ErrorResult("Araba bakımda.");
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIsNotExists(int carId) {
		if(!this.carService.checkCarExists(carId).isSuccess()) {
			return new ErrorResult("Böyle bir araba yok.");
		}
		
		return new SuccessResult();
	}

	private Result checkIfUserNotExists(int userId){
		if(!this.userService.checkUserExists(userId).isSuccess()){
			return new  ErrorResult("Böyle bir kullanıcı yok");
		}
		return  new SuccessResult();
	}

	private Result checkIfLimitIsInsufficient(PayCreditCardRequest payCreditCardRequest){
		if(!this.paymentService.payByCreditCard(payCreditCardRequest).isSuccess()){
			return new ErrorResult("Limit is insufficient!");
		}
		return new SuccessResult();
	}


}

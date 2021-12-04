package com.etiya.rentACarSpring.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.IndividualCustomer;
import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.InvoiceService;
import com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACarSpring.entities.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	
	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao,ModelMapperService modelMapperService,RentalService rentalService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService=modelMapperService;
		this.rentalService=rentalService;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result= BusinessRules.run(checkIfInvoiceNotExists(createInvoiceRequest.getRentalId()),checkIfReturnDateIsNull(createInvoiceRequest.getRentalId()));

		if(result!=null){
			return result;
		}

		Invoice invoice=this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		int countOfRentalDays=this.rentalService.getDayBetweenDatesOfRental(createInvoiceRequest.getRentalId()).getData();
		invoice.setCountOfRentalDays(countOfRentalDays);
		invoice.setInvoiceAmount(calculateTotalPrice(createInvoiceRequest));
		invoice.setInvoiceNumber(createInvoiceNumber(createInvoiceRequest.getRentalId()).getData());
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.InvoiceIsCreated);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice=this.invoiceDao.getById(updateInvoiceRequest.getId());
		invoice.setInvoiceNumber(updateInvoiceRequest.getInvoiceNumber());
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.DataUpdated);
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		this.invoiceDao.deleteById(deleteInvoiceRequest.getId());
		return new SuccessResult(Messages.DataDeleted);
	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getAll() {
		List<Invoice> result = this.invoiceDao.findAll();
		List<InvoiceSearchListDto> response = result.stream().map(invoice -> modelMapperService
				.forDto().map(invoice, InvoiceSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<InvoiceSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CustomerInvoiceDetail>> getAllInvoicesOfRelevantCustomer(int customerId) {
		List<CustomerInvoiceDetail> customerInvoiceDetails=this.invoiceDao.getAllInvoicesOfRelevantCustomer(customerId);
		return new SuccessDataResult<>(customerInvoiceDetails);
	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getInvoicesByCreationDateBetweeen(LocalDate firstDate, LocalDate secondDate) {
		List<InvoiceSearchListDto> result=this.invoiceDao.getInvoicesByCreationDateBetweeen(firstDate,secondDate).stream()
				.map(invoice -> this.modelMapperService.forDto().map(invoice,InvoiceSearchListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<>(result);
	}


	private DataResult<String> createInvoiceNumber(int rentalId){
		//Date now=new Date();
		LocalDate now=LocalDate.now();
		int currentYear=now.getYear();
		String invoiceNumber=currentYear+"FTR"+rentalId;
		return new SuccessDataResult<>(invoiceNumber);
	}

	private double calculateTotalPrice(CreateInvoiceRequest createInvoiceRequest){
		int countOfRentalDays=this.rentalService.getDayBetweenDatesOfRental(createInvoiceRequest.getRentalId()).getData();
		double dailyPriceOfRentedCar=this.rentalService.getDailyPriceOfRentedCar(createInvoiceRequest.getRentalId()).getData();
		double additionalItemTotalPrice=this.rentalService.getAdditionalItemsTotalPrice(createInvoiceRequest.getRentalId())*countOfRentalDays;
		double totalPrice=(countOfRentalDays*dailyPriceOfRentedCar)+additionalItemTotalPrice;
		double additionalServicePrice=500;

		if(!this.rentalService.checkCarIsReturnedToSameCity(createInvoiceRequest.getRentalId()).isSuccess()){
			totalPrice=totalPrice+additionalServicePrice;
		}
		return totalPrice;

	}

	private Result checkIfInvoiceNotExists(int rentalId){
		if(!this.invoiceDao.existsInvoiceByRental_Id(rentalId)){
			return new SuccessResult();
		}
		return new ErrorResult(Messages.InvoiceIsAlreadyExists);
	}

	private Result checkIfReturnDateIsNull(int rentalId){
		if(this.rentalService.getById(rentalId).getData().getReturnDate()==null){
			return new ErrorResult(Messages.MustBeEnteredReturnDateBeforeCreatInvoice);
		}
		return new SuccessResult();
	}


}

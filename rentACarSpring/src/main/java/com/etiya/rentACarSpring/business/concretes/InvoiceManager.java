package com.etiya.rentACarSpring.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.SuccessDataResult;
import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.InvoiceService;
import com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccessResult;
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

		Invoice invoice=this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		int countOfRentalDays=this.rentalService.getDayBetweenDatesOfRental(createInvoiceRequest.getRentalId()).getData();
		invoice.setCountOfRentalDays(countOfRentalDays);
		invoice.setInvoiceAmount(calculateTotalPrice(createInvoiceRequest));
		invoice.setInvoiceNumber(createInvoiceNumber(createInvoiceRequest.getRentalId()).getData());
		this.invoiceDao.save(invoice);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getAll() {
		// TODO Auto-generated method stub
		return null;
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
		double totalPrice=countOfRentalDays*dailyPriceOfRentedCar;
		double additionalServicePrice=500;
		if(!this.rentalService.checkCarIsReturnedToSameCity(createInvoiceRequest.getRentalId()).isSuccess()){
			totalPrice=totalPrice+additionalServicePrice;
		}
		return totalPrice;

	}


}

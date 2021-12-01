package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.InvoiceService;
import com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid  CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid  UpdateInvoiceRequest updateInvoiceRequest) {

		return this.invoiceService.update(updateInvoiceRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
		
		return this.invoiceService.delete(deleteInvoiceRequest);

	}

	@GetMapping("all")
	public DataResult<List<InvoiceSearchListDto>> getAll() {
		return this.invoiceService.getAll();

	}

	@GetMapping("allInvoicesOfRelevantCustomer")
	public DataResult<List<CustomerInvoiceDetail>> getAllInvoicesOfRelevantCustomer(int customerId) {
		return this.invoiceService.getAllInvoicesOfRelevantCustomer(customerId);

	}

}

package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;
import com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;

public interface InvoiceService {

	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	DataResult<List<InvoiceSearchListDto>> getAll();
	DataResult<List<CustomerInvoiceDetail>> getAllInvoicesOfRelevantCustomer(int customerId);
}

package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    boolean existsInvoiceByRental_Id(int rentalId);

@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail(u.userId,  i.invoiceNumber ,i.invoiceAmount,i.countOfRentalDays, r.car.id) From ApplicationUser u inner join u.rentals r inner join r.invoice i where u.userId=:userId")
List<CustomerInvoiceDetail> getAllInvoicesOfRelevantCustomer(int userId);

//@Query(value = "SELECT * FROM Invoices WHERE Creation_Date>=?1  AND Creation_Date<=?2",nativeQuery = true)
//List<InvoiceSearchListDto> getInvoicesByCreationDateBetweeen(LocalDate firstDate, LocalDate secondDate);

@Query("Select new com.etiya.rentACarSpring.business.dtos.InvoiceSearchListDto(i.id, i.invoiceNumber,i.invoiceAmount,i.countOfRentalDays,i.creationDate,i.rental.id) From Invoice i where i.creationDate>=:firstDate and i.creationDate<=:secondDate ")
List<InvoiceSearchListDto> getInvoicesByCreationDateBetweeen(LocalDate firstDate, LocalDate secondDate);

}

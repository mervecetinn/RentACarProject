package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.complexTypes.CustomerInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    @Query(value = "select u.user_id,  i.invoice_number ,i.invoice_amount,i.count_of_rental_days,r.car_id from users u inner join rentals r on u.user_id=r.user_id inner join invoices i on r.rental_id=i.rental_id where u.user_id=?1",nativeQuery = true)
    List<CustomerInvoiceDetail> getAllInvoicesOfRelevantCustomer(int userId);

}

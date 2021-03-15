package service;

import model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceCustomer {
    List<Customer>findAll();
    Page<Customer>findAll(Pageable pageable);
    Customer findById(Long id);
    Customer save(Customer customer);
    void delete(Long id);
    List<Customer>findByName(String name);
}

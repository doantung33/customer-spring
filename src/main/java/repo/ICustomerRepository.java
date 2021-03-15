package repo;

import model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    @Query(value = "select * from customer where firstName like ?",nativeQuery = true)
    List<Customer>search(String name);
}

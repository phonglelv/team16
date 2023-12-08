package poly.tt.thuexe.Repository;

//import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.tt.thuexe.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT DISTINCT ar.customer from Authority ar Where ar.role.id IN ('DIRE','STAF')")
    List<Customer> getAdministrators();

   // void save(UserDetails user);
   Page<Customer> findAllByFullnameLike(String keywords, Pageable pageable);
    Customer findByEmail(String email);
    @Query("SELECT COUNT(c) FROM Customer c")
    long countTotalCustomers();

    Customer findByUsername (String username);
   
    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    Customer getCustomerByUsername(String username);
}

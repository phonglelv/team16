package poly.tt.thuexe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.tt.thuexe.Entity.Authority;
import poly.tt.thuexe.Entity.Customer;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    @Query("Select Distinct a from Authority a where a.customer in ?1")
    List<Authority> authritiOff(List<Customer> customers);

}

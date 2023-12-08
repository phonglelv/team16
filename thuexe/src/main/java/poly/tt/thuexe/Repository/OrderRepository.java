package poly.tt.thuexe.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.tt.thuexe.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT a FROM Order a WHERE a.customer.username LIKE %?1%")
    Page<Order> findByUsername(String username, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.username=?1")
	List<Order> findByUsername(String username);
	

}

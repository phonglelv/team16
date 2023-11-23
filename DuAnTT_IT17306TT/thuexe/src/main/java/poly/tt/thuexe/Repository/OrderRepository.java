package poly.tt.thuexe.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.tt.thuexe.Entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT a FROM Order a WHERE a.customer.username LIKE %?1%")
    Page<Order> findByUsername(String username, Pageable pageable);



}

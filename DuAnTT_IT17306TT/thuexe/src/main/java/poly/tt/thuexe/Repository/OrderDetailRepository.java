package poly.tt.thuexe.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.tt.thuexe.Entity.Order;
import poly.tt.thuexe.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT a FROM OrderDetail a WHERE a.product.name LIKE %?1%")
    Page<OrderDetail> findByNameProduct(String username, Pageable pageable);

    // Nếu bạn muốn tính tổng theo một đơn hàng cụ thể, bạn có thể sử dụng thêm tham số
    @Query("SELECT SUM(od.price) FROM OrderDetail od")
    Double getTotalOrderAmount();
}

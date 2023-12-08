package poly.tt.thuexe.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.tt.thuexe.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer > {
    @Query("Select p from Product p where p.category.id=?1")
    List<Product> findByCategoryId(String id);

    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    Page<Product> findByAllCategoryId(@Param("categoryId") String categoryId, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price >= 500000")
    Page<Product> findAllWithMaxPrice(Pageable pageable);
}

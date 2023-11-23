package poly.tt.thuexe.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import poly.tt.thuexe.Entity.Category;

public interface CategotyRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAllByNameLike(String keywords, Pageable pageable);
}

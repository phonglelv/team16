package poly.tt.thuexe.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAllByReportNameLike(String keywords, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Report c")
    long countTotalReports();
}

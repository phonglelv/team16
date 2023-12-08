package poly.tt.thuexe.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Report;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ReportService {
    void flush();

    <S extends Report> S saveAndFlush(S entity);
    Page<Report> findAllByReportNameLike(String keywords, Pageable pageable);
    <S extends Report> List<S> saveAllAndFlush(Iterable<S> entities);
    long getTotalReports();
    @Deprecated
    void deleteInBatch(Iterable<Report> entities);

    void deleteAllInBatch(Iterable<Report> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Report getOne(Long aLong);

    @Deprecated
    Report getById(Long aLong);

    Report getReferenceById(Long aLong);

    <S extends Report> List<S> findAll(Example<S> example);

    <S extends Report> List<S> findAll(Example<S> example, Sort sort);

    <S extends Report> List<S> saveAll(Iterable<S> entities);

    List<Report> findAll();

    List<Report> findAllById(Iterable<Long> longs);

    <S extends Report> S save(S entity);

    Optional<Report> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Report entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Report> entities);

    void deleteAll();

    List<Report> findAll(Sort sort);

    Page<Report> findAll(Pageable pageable);

    <S extends Report> Optional<S> findOne(Example<S> example);

    <S extends Report> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Report> long count(Example<S> example);

    <S extends Report> boolean exists(Example<S> example);


}

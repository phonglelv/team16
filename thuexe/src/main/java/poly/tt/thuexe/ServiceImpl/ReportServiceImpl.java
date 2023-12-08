package poly.tt.thuexe.ServiceImpl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Entity.Report;
import poly.tt.thuexe.Repository.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ReportServiceImpl implements poly.tt.thuexe.Service.ReportService {

    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    @Override
    public Page<Report> findAllByReportNameLike(String keywords, Pageable pageable) {
        return reportRepository.findAllByReportNameLike(keywords,pageable);
    }

    public <S extends Report> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    public long getTotalReports() {
        return reportRepository.countTotalReports();
    }
    @Override
    public void flush() {
        reportRepository.flush();
    }

    @Override
    public <S extends Report> S saveAndFlush(S entity) {
        return reportRepository.saveAndFlush(entity);
    }



    /**
     * @param entities
     * @deprecated
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Report> entities) {
        reportRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Report> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }


    @Override
    public void deleteAllInBatch() {
        reportRepository.deleteAllInBatch();
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    @Deprecated
    public Report getOne(Long aLong) {
        return reportRepository.getOne(aLong);
    }

    public Report getById(Long aLong) {
        return null;
    }

    public Report getReferenceById(Long aLong) {
        return null;
    }


    @Override
    public <S extends Report> List<S> findAll(Example<S> example) {
        return reportRepository.findAll(example);
    }

    @Override
    public <S extends Report> List<S> findAll(Example<S> example, Sort sort) {
        return reportRepository.findAll(example, sort);
    }

    @Override
    public <S extends Report> List<S> saveAll(Iterable<S> entities) {
        return reportRepository.saveAll(entities);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findAllById(Iterable<Long> longs) {
        return reportRepository.findAllById(longs);
    }

    @Override
    public <S extends Report> S save(S entity) {
        return reportRepository.save(entity);
    }

    @Override
    public Optional<Report> findById(Long aLong) {
        return reportRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return reportRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return reportRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        reportRepository.deleteById(aLong);
    }

    @Override
    public void delete(Report entity) {
        reportRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {

    }


    @Override
    public void deleteAll(Iterable<? extends Report> entities) {
        reportRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        reportRepository.deleteAll();
    }

    @Override
    public List<Report> findAll(Sort sort) {
        return reportRepository.findAll(sort);
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public <S extends Report> Optional<S> findOne(Example<S> example) {
        return reportRepository.findOne(example);
    }

    @Override
    public <S extends Report> Page<S> findAll(Example<S> example, Pageable pageable) {
        return reportRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Report> long count(Example<S> example) {
        return reportRepository.count(example);
    }

    @Override
    public <S extends Report> boolean exists(Example<S> example) {
        return reportRepository.exists(example);
    }


}

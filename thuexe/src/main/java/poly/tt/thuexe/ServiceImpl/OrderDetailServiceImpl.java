package poly.tt.thuexe.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import poly.tt.thuexe.Entity.OrderDetail;
import poly.tt.thuexe.Repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements poly.tt.thuexe.Service.OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
    @Override
    public Page<OrderDetail> findByNameProduct(String username, Pageable pageable) {

        return orderDetailRepository.findByNameProduct(username, pageable);
    }

    public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public Double getTotalOrderAmountByOrderId() {
        return orderDetailRepository.getTotalOrderAmount();
    }
    @Override
    public void flush() {
        orderDetailRepository.flush();
    }

    @Override
    public <S extends OrderDetail> S saveAndFlush(S entity) {
        return orderDetailRepository.saveAndFlush(entity);
    }



    /**
     * @param entities
     * @deprecated
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<OrderDetail> entities) {
        orderDetailRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<OrderDetail> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }


    @Override
    public void deleteAllInBatch() {
        orderDetailRepository.deleteAllInBatch();
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    @Deprecated
    public OrderDetail getOne(Long aLong) {
        return orderDetailRepository.getOne(aLong);
    }

    public OrderDetail getById(Long aLong) {
        return null;
    }

    public OrderDetail getReferenceById(Long aLong) {
        return null;
    }


    @Override
    public <S extends OrderDetail> List<S> findAll(Example<S> example) {
        return orderDetailRepository.findAll(example);
    }

    @Override
    public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
        return orderDetailRepository.findAll(example, sort);
    }

    @Override
    public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
        return orderDetailRepository.saveAll(entities);
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAllById(Iterable<Long> longs) {
        return orderDetailRepository.findAllById(longs);
    }

    @Override
    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    public Optional<OrderDetail> findById(Long aLong) {
        return orderDetailRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderDetailRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return orderDetailRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        orderDetailRepository.deleteById(aLong);
    }

    @Override
    public void delete(OrderDetail entity) {
        orderDetailRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {

    }


    @Override
    public void deleteAll(Iterable<? extends OrderDetail> entities) {
        orderDetailRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        orderDetailRepository.deleteAll();
    }

    @Override
    public List<OrderDetail> findAll(Sort sort) {
        return orderDetailRepository.findAll(sort);
    }

    @Override
    public Page<OrderDetail> findAll(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }

    @Override
    public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
        return orderDetailRepository.findOne(example);
    }

    @Override
    public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return orderDetailRepository.findAll(example, pageable);
    }

    @Override
    public <S extends OrderDetail> long count(Example<S> example) {
        return orderDetailRepository.count(example);
    }

    @Override
    public <S extends OrderDetail> boolean exists(Example<S> example) {
        return orderDetailRepository.exists(example);
    }


}

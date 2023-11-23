package poly.tt.thuexe.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import poly.tt.thuexe.Entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(JsonNode orderData);


    Page<Order> findByUsername(String username,Pageable pageable);
    void flush();

    <S extends Order> S saveAndFlush(S entity);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Order> entities);

    void deleteAllInBatch(Iterable<Order> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Order getOne(Long aLong);

    @Deprecated
    Order getById(Long aLong);

    Order getReferenceById(Long aLong);

    <S extends Order> List<S> findAll(Example<S> example);

    <S extends Order> List<S> findAll(Example<S> example, Sort sort);

    <S extends Order> List<S> saveAll(Iterable<S> entities);

    List<Order> findAll();

    List<Order> findAllById(Iterable<Long> longs);

    <S extends Order> S save(S entity);

    Optional<Order> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Order entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Order> entities);

    void deleteAll();

    List<Order> findAll(Sort sort);

    Page<Order> findAll(Pageable pageable);

    <S extends Order> Optional<S> findOne(Example<S> example);

    <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Order> long count(Example<S> example);

    <S extends Order> boolean exists(Example<S> example);
}

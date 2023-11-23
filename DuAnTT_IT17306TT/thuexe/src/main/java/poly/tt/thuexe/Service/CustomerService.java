package poly.tt.thuexe.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.security.core.userdetails.UserDetails;
import poly.tt.thuexe.Entity.Category;
import poly.tt.thuexe.Entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CustomerService {
    List<Customer> getAdministrators();

   // void save(UserDetails user);
   Page<Customer> findAllByFullnameLike(String keywords, Pageable pageable);
    Customer findByEmail(String email);

    void flush();
 long getTotalCustomers();
 <S extends Customer> S saveAndFlush(S entity);

    <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Customer> entities);

    void deleteAllInBatch(Iterable<Customer> entities);

    void deleteAllByIdInBatch(Iterable<String> strings);

    void deleteAllInBatch();

    @Deprecated
    Customer getOne(String s);

    @Deprecated
    Customer getById(String s);

    Customer getReferenceById(String s);

    <S extends Customer> List<S> findAll(Example<S> example);

    <S extends Customer> List<S> findAll(Example<S> example, Sort sort);

    <S extends Customer> List<S> saveAll(Iterable<S> entities);

    List<Customer> findAll();

    List<Customer> findAllById(Iterable<String> strings);

    <S extends Customer> S save(S entity);

    Optional<Customer> findById(String s);

    boolean existsById(String s);

    long count();

    void deleteById(String s);

    void delete(Customer entity);

    void deleteAllById(Iterable<? extends String> strings);

    void deleteAll(Iterable<? extends Customer> entities);

    void deleteAll();

    List<Customer> findAll(Sort sort);

    Page<Customer> findAll(Pageable pageable);

    <S extends Customer> Optional<S> findOne(Example<S> example);

    <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Customer> long count(Example<S> example);

    <S extends Customer> boolean exists(Example<S> example);

    <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}

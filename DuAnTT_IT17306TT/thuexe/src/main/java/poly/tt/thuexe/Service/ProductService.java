package poly.tt.thuexe.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import poly.tt.thuexe.Entity.Category;
import poly.tt.thuexe.Entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ProductService {
    List<Product> findByCategoryId(String cid);
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
    Product create(Product product);
    Page<Product> findByAllCategoryId(String categoryId, Pageable pageable);


    Product update(Product product);

    void flush();

    <S extends Product> S saveAndFlush(S entity);

    <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Product> entities);

    void deleteAllInBatch(Iterable<Product> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Product getOne(Integer integer);

    @Deprecated
    Product getById(Integer integer);

    Product getReferenceById(Integer integer);

    <S extends Product> List<S> findAll(Example<S> example);

    <S extends Product> List<S> findAll(Example<S> example, Sort sort);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Integer> integers);

    <S extends Product> S save(S entity);

    Optional<Product> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Product entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Product> entities);

    void deleteAll();

    List<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    <S extends Product> Optional<S> findOne(Example<S> example);

    <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Product> long count(Example<S> example);

    <S extends Product> boolean exists(Example<S> example);

    <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}

package poly.tt.thuexe.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import poly.tt.thuexe.Entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CategotyServiceImpl {
    void flush();
    Page<Category> findAllByNameLike(String keywords, Pageable pageable);
    <S extends Category> S saveAndFlush(S entity);

    <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Category> entities);

    void deleteAllInBatch(Iterable<Category> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Category getOne(Integer integer);

    @Deprecated
    Category getById(Integer integer);

    Category getReferenceById(Integer integer);

    <S extends Category> List<S> findAll(Example<S> example);

    <S extends Category> List<S> findAll(Example<S> example, Sort sort);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    List<Category> findAll();

    List<Category> findAllById(Iterable<Integer> integers);

    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Category entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Category> entities);

    void deleteAll();

    List<Category> findAll(Sort sort);

    Page<Category> findAll(Pageable pageable);

    <S extends Category> Optional<S> findOne(Example<S> example);

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Category> long count(Example<S> example);

    <S extends Category> boolean exists(Example<S> example);

    <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}

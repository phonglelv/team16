package poly.tt.thuexe.ServiceImpl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import poly.tt.thuexe.Entity.Category;
import poly.tt.thuexe.Repository.CategotyRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategotyServiceImpl implements poly.tt.thuexe.Service.CategotyServiceImpl {
    CategotyRepository categotyRepository;

    public CategotyServiceImpl(CategotyRepository categotyRepository) {
        this.categotyRepository = categotyRepository;
    }


    @Override
    public void flush() {
        categotyRepository.flush();
    }

    @Override
    public Page<Category> findAllByNameLike(String keywords, Pageable pageable) {
        return categotyRepository.findAllByNameLike(keywords,pageable);
    }

    @Override
    public <S extends Category> S saveAndFlush(S entity) {
        return categotyRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
        return categotyRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Category> entities) {
        categotyRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Category> entities) {
        categotyRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        categotyRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        categotyRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Category getOne(Integer integer) {
        return categotyRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Category getById(Integer integer) {
        return categotyRepository.getById(integer);
    }

    @Override
    public Category getReferenceById(Integer integer) {
        return categotyRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return categotyRepository.findAll(example);
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return categotyRepository.findAll(example, sort);
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categotyRepository.saveAll(entities);
    }

    @Override
    public List<Category> findAll() {
        return categotyRepository.findAll();
    }

    @Override
    public List<Category> findAllById(Iterable<Integer> integers) {
        return categotyRepository.findAllById(integers);
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categotyRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return categotyRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return categotyRepository.existsById(integer);
    }

    @Override
    public long count() {
        return categotyRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        categotyRepository.deleteById(integer);
    }

    @Override
    public void delete(Category entity) {
        categotyRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        categotyRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        categotyRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        categotyRepository.deleteAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categotyRepository.findAll(sort);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categotyRepository.findAll(pageable);
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categotyRepository.findOne(example);
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return categotyRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return categotyRepository.count(example);
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return categotyRepository.exists(example);
    }

    @Override
    public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return categotyRepository.findBy(example, queryFunction);
    }
}

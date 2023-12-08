package poly.tt.thuexe.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import poly.tt.thuexe.Entity.Product;
import poly.tt.thuexe.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements poly.tt.thuexe.Service.ProductService {

    ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByCategoryId(String cid) {

        return productRepository.findByCategoryId(cid);
    }
    @Override
    public Page<Product> findByAllCategoryId(String categoryId, Pageable pageable) {
        return productRepository.findByAllCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Product> findAllWithMaxPrice(Pageable pageable) {
        return productRepository.findAllWithMaxPrice(pageable);
    }
    @Override
    public Page<Product> findAllByNameLike(String keywords, Pageable pageable) {
        return productRepository.findAllByNameLike(keywords,pageable);
    }
    @Override
    public Product create(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }


    @Override
    public void flush() {
        productRepository.flush();
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }


    /**
     * @param entities
     * @deprecated
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Product> entities) {
        productRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }


    @Override
    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    @Deprecated
    public Product getOne(Integer integer) {
        return productRepository.getOne(integer);
    }

    public Product getById(Integer integer) {
        return null;
    }

    public Product getReferenceById(Integer integer) {
        return null;
    }


    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return productRepository.findAll(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return productRepository.findAll(example, sort);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllById(Iterable<Integer> integers) {
        return productRepository.findAllById(integers);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productRepository.existsById(integer);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {

    }


    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        productRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return productRepository.findOne(example);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return productRepository.count(example);
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }


}

package poly.tt.thuexe.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.tt.thuexe.Entity.Order;
import poly.tt.thuexe.Entity.OrderDetail;
import poly.tt.thuexe.Repository.OrderDetailRepository;
import poly.tt.thuexe.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements poly.tt.thuexe.Service.OrderService {
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository detailDao;
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
public Order create(JsonNode orderData) {
    ObjectMapper mapper = new ObjectMapper();
    Order order = mapper.convertValue(orderData, Order.class);
    orderRepository.save(order);

    TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
    };

    List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
            .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
    detailDao.saveAll(details); // Thêm @Autowired trước OrderDetailDao detailDao;

    return order;
}
@Override
public List<Order> findByUsername1(String username) {
	
	return orderRepository.findByUsername(username);
}

@Override
public void deleteOrderById(Long id) {
	orderRepository.deleteById(id);
	
}

    @Override
    public Page<Order> findByUsername(String username,Pageable pageable) {

        return orderRepository.findByUsername(username, pageable);
    }


    @Override
    public void flush() {
        orderRepository.flush();
    }

    @Override
    public <S extends Order> S saveAndFlush(S entity) {
        return orderRepository.saveAndFlush(entity);
    }

    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }


    /**
     * @param entities
     * @deprecated
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Order> entities) {
        orderRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Order> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }


    @Override
    public void deleteAllInBatch() {
        orderRepository.deleteAllInBatch();
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    @Deprecated
    public Order getOne(Long aLong) {
        return orderRepository.getOne(aLong);
    }

    public Order getById(Long aLong) {
        return null;
    }

    public Order getReferenceById(Long aLong) {
        return null;
    }


    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        return orderRepository.findAll(example);
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return orderRepository.findAll(example, sort);
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return orderRepository.saveAll(entities);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllById(Iterable<Long> longs) {
        return orderRepository.findAllById(longs);
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return orderRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {

    }


    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        orderRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public List<Order> findAll(Sort sort) {
        return orderRepository.findAll(sort);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return orderRepository.findOne(example);
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return orderRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        return orderRepository.count(example);
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        return orderRepository.exists(example);
    }


}

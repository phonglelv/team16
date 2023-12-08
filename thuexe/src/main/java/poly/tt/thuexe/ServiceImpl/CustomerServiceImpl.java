package poly.tt.thuexe.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.tt.thuexe.Entity.Customer;
import poly.tt.thuexe.Model.CustomerModel;
import poly.tt.thuexe.Repository.CustomerRepository;
import poly.tt.thuexe.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
   CustomerRepository customerRepository;

   @Autowired
   PasswordEncoder passwordEncoder;


    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<Customer> optionalCustomer = customerRepository.findById(username);

        if (optionalCustomer.isEmpty()) {
            // Xử lý khi không tìm thấy khách hàng
            return false;
        }

        Customer customer = optionalCustomer.get();

        // Kiểm tra mật khẩu hiện tại
        if (oldPassword == null || !passwordEncoder.matches(oldPassword, customer.getPassword())) {
            // Mật khẩu hiện tại không chính xác hoặc null
            return false;
        }

        // Kiểm tra mật khẩu mới có khác mật khẩu hiện tại không
        if (newPassword.equals(oldPassword)) {
            // Mật khẩu mới trùng với mật khẩu hiện tại
            return false;
        }

        // Cập nhật mật khẩu mới
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        return true;
    }

   
    

    public void updatePassword(Customer customer, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
    }
    @Override
    public Customer saveCode(CustomerModel customerModel) {
        String rawPassword = customerModel.getPassword();
        if (rawPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        Customer customer = new Customer(
                customerModel.getUsername(),
                passwordEncoder.encode(rawPassword),
                customerModel.getAddress(),
                customerModel.getEmail(),
                customerModel.getFullname(),
                customerModel.getPhone()
        );

        return customerRepository.save(customer);
    }




    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.getCustomerByUsername(username);
    }

    @Override
    public void saveCustomer(Customer customer) {
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
          customer.getUsername();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAdministrators() {
        return customerRepository.getAdministrators();
    }

  //  @Override
   // public void save(UserDetails user) {
     //   customerRepository.save(user);
  //  }
  @Override
  public Page<Customer> findAllByFullnameLike(String keywords, Pageable pageable) {
      return customerRepository.findAllByFullnameLike(keywords,pageable);
  }
    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }



    @Override
    public long getTotalCustomers() {
        return customerRepository.countTotalCustomers();
    }
    @Override
    public void flush() {
        customerRepository.flush();
    }

    @Override
    public <S extends Customer> S saveAndFlush(S entity) {
        return customerRepository.saveAndFlush(entity);
    }

    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }


    /**
     * @param entities
     * @deprecated
     */
    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Customer> entities) {
        customerRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Customer> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }


    @Override
    public void deleteAllInBatch() {
        customerRepository.deleteAllInBatch();
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    @Deprecated
    public Customer getOne(String s) {
        return customerRepository.getOne(s);
    }

    public Customer getById(String s) {
        return null;
    }

    public Customer getReferenceById(String s) {
        return null;
    }


    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return customerRepository.findAll(example);
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return customerRepository.findAll(example, sort);
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        return customerRepository.saveAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllById(Iterable<String> strings) {
        return customerRepository.findAllById(strings);
    }

    @Override
    public <S extends Customer> S save(S entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return customerRepository.save(entity);
    }

    @Override
    public Optional<Customer> findById(String s) {
        return customerRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return customerRepository.existsById(s);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void deleteById(String s) {
        customerRepository.deleteById(s);
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends String> strings) {

    }


    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        customerRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return customerRepository.findAll(sort);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return customerRepository.findOne(example);
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return customerRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return customerRepository.count(example);
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return customerRepository.exists(example);
    }

    @Override
    public Customer saveToken(Customer customer) {
      return customerRepository.save(customer);
    }


}

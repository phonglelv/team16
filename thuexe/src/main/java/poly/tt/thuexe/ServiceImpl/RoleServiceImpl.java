package poly.tt.thuexe.ServiceImpl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import poly.tt.thuexe.Entity.Role;
import poly.tt.thuexe.Repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class RoleServiceImpl {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void flush() {
        roleRepository.flush();
    }

    public <S extends Role> S saveAndFlush(S entity) {
        return roleRepository.saveAndFlush(entity);
    }

    /**
     * @param entities
     * @deprecated
     */
    @Deprecated
    public void deleteInBatch(Iterable<Role> entities) {
        roleRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        roleRepository.deleteAllInBatch();
    }

    /**
     * @param s
     * @deprecated
     */
    @Deprecated
    public Role getOne(String s) {
        return roleRepository.getOne(s);
    }


    public <S extends Role> List<S> findAll(Example<S> example) {
        return roleRepository.findAll(example);
    }

    public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return roleRepository.findAll(example, sort);
    }

    public <S extends Role> List<S> saveAll(Iterable<S> entities) {
        return roleRepository.saveAll(entities);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<Role> findAllById(Iterable<String> strings) {
        return roleRepository.findAllById(strings);
    }

    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    public Optional<Role> findById(String s) {
        return roleRepository.findById(s);
    }

    public boolean existsById(String s) {
        return roleRepository.existsById(s);
    }

    public long count() {
        return roleRepository.count();
    }

    public void deleteById(String s) {
        roleRepository.deleteById(s);
    }

    public void delete(Role entity) {
        roleRepository.delete(entity);
    }


    public void deleteAll(Iterable<? extends Role> entities) {
        roleRepository.deleteAll(entities);
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public List<Role> findAll(Sort sort) {
        return roleRepository.findAll(sort);
    }

    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public <S extends Role> Optional<S> findOne(Example<S> example) {
        return roleRepository.findOne(example);
    }

    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return roleRepository.findAll(example, pageable);
    }

    public <S extends Role> long count(Example<S> example) {
        return roleRepository.count(example);
    }

    public <S extends Role> boolean exists(Example<S> example) {
        return roleRepository.exists(example);
    }

}

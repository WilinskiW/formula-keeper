package com.wilinskiw.porfolio.formula_data.repository;

import com.wilinskiw.porfolio.formula_data.model.Role;
import com.wilinskiw.porfolio.formula_data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByUser(User user);
}

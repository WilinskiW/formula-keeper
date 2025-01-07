package com.wilinskiw.portfolio.formula_data.repository;

import com.wilinskiw.portfolio.formula_data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    List<User> searchAllByEmail(String email);

    User findUserById(Long id);

    User findUserByEmail(String email);
}

package com.wilinskiw.porfolio.formula_data.repository;

import com.wilinskiw.porfolio.formula_data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    int countById(Long id);
}

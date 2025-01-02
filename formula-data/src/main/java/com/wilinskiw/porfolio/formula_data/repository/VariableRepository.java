package com.wilinskiw.porfolio.formula_data.repository;

import com.wilinskiw.porfolio.formula_data.model.Variable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends CrudRepository<Variable, Long> {

}

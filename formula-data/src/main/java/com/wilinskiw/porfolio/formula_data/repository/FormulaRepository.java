package com.wilinskiw.porfolio.formula_data.repository;

import com.wilinskiw.porfolio.formula_data.model.Formula;
import com.wilinskiw.porfolio.formula_data.model.Tag;
import com.wilinskiw.porfolio.formula_data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FormulaRepository extends CrudRepository<Formula, Long> {
    List<Formula> getFormulasByUser(User user);
    List<Formula> getFormulasByTags(Set<Tag> tags);
}

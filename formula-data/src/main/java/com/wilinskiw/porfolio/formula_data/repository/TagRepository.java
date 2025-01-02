package com.wilinskiw.porfolio.formula_data.repository;

import com.wilinskiw.porfolio.formula_data.model.Formula;
import com.wilinskiw.porfolio.formula_data.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByName(String name);
    List<Tag> findTagByFormulas(Set<Formula> formulas);
}

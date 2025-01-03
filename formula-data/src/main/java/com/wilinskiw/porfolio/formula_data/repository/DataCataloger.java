package com.wilinskiw.porfolio.formula_data.repository;

/**
 * Aggregate all repository methods to one object
 */
public interface DataCataloger {
    FormulaRepository getFormulaRepository();
    RoleRepository getRoleRepository();
    TagRepository getTagRepository();
    UserRepository getUserRepository();
    VariableRepository getVariableRepository();
}

package com.wilinskiw.porfolio.formula_data.repository;

public interface DataCataloger {
    FormulaRepository getFormulaRepository();
    RoleRepository getRoleRepository();
    TagRepository getTagRepository();
    UserRepository getUserRepository();
    VariableRepository getVariableRepository();
}

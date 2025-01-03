package com.wilinskiw.porfolio.formula_data.repository;

public class FormulaKeeperDataCatalog implements DataCataloger{
    private final FormulaRepository formulaRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final RoleRepository roleRepository;
    private final VariableRepository variableRepository;

    public FormulaKeeperDataCatalog(FormulaRepository formulaRepository,
                                    UserRepository userRepository, TagRepository tagRepository,
                                    RoleRepository repository, VariableRepository variableRepository) {
        this.formulaRepository = formulaRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.roleRepository = repository;
        this.variableRepository = variableRepository;
    }

    @Override
    public FormulaRepository getFormulaRepository() {
        return formulaRepository;
    }

    @Override
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    @Override
    public TagRepository getTagRepository() {
        return tagRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public VariableRepository getVariableRepository() {
        return variableRepository;
    }
}

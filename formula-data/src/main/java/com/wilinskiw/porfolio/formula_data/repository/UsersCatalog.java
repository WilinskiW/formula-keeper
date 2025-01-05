package com.wilinskiw.porfolio.formula_data.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UsersCatalog implements UsersCataloger {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UsersCatalog(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }
}

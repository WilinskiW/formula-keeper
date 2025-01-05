package com.wilinskiw.porfolio.formula_data.repository;


/**
 * Aggregate all repository for managing users
 */
public interface UsersCataloger {
    UserRepository getUserRepository();

    RoleRepository getRoleRepository();
}

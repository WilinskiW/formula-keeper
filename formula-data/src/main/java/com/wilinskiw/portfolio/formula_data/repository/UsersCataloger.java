package com.wilinskiw.portfolio.formula_data.repository;


/**
 * Aggregate all repository for managing users
 */
public interface UsersCataloger {
    UserRepository getUserRepository();

    RoleRepository getRoleRepository();
}

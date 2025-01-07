package com.wilinskiw.portfolio.formula_data.service;

import com.wilinskiw.portfolio.formula_data.model.Role;
import com.wilinskiw.portfolio.formula_data.model.User;
import com.wilinskiw.portfolio.formula_data.repository.RoleRepository;
import com.wilinskiw.portfolio.formula_data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDataService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.of(userRepository.findUserByEmail(email));
    }

    public List<Role> findUserRolesByUser(User user) {
        if(user != null){
            return roleRepository.findRoleByUser(user);
        }
        return new ArrayList<>();
    }

   public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
   }

   public boolean checkUserCredentials(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email,password) != null;
   }

}

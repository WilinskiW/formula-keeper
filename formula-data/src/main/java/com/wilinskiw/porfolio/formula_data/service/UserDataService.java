package com.wilinskiw.porfolio.formula_data.service;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {
    private final UserRepository userRepository;

    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

   public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
   }

   public boolean checkUserCredentials(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email,password) != null;
   }

}

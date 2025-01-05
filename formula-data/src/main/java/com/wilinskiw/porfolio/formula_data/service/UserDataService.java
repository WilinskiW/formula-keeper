package com.wilinskiw.porfolio.formula_data.service;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

   public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
   }

   public boolean checkUserCredentials(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email,password) != null;
   }

}

package com.wilinskiw.porfolio.formula_data;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        User savedUser = userRepository.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void findUserByEmailTest() {
        User user = new User();
        user.setEmail("test@gmail.com");
        User savedUser = userRepository.save(user);

    }



}

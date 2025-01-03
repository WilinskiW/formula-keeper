package com.wilinskiw.porfolio.formula_data;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setFirstName("Joe");
        user.setLastName("Doe");

        User savedUser = userRepository.save(user);
        User retrievedUser = userRepository.findById(savedUser.getId()).get();
        assertEquals(4, retrievedUser.getId());
    }

    @Test
    public void findAllUsersTest() {
        long userCount = userRepository.count();
        System.out.println(userCount);
        assertEquals(3, userCount);
    }

    @Test
    public void findUseByEmailAndPasswordTest() {
        User user = userRepository.findUserByEmailAndPassword("user1@example.com", "password123");
        assertEquals(userRepository.findById(1L).get(), user);
    }

    @Test
    public void isEmailExistTest() {
        boolean resultTrue = userRepository.existsByEmail("user1@example.com");
        boolean resultFalse = userRepository.existsByEmail("user-1000@example.com");
        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }
}

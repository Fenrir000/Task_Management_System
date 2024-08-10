package com.example.task_management_system.repos;

import com.example.task_management_system.model.Role;
import com.example.task_management_system.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void saveUser() {
        User entity = User.builder()
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        User savedUser = userRepository.save(entity);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }



    @Test
    void findByEmail() {
        // Given
        String email = "emai@gmail.com";
        User entity = User.builder()
                .email(email)
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        userRepository.save(entity);
        Optional<User> foundUser = userRepository.findByEmail(email);
        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
    }

    @Test
    void existsByUsername() {
        String username = "newUsername";
        User entity = User.builder()
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username(username)
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        userRepository.save(entity);

        boolean exists = userRepository.existsByUsername(username);

        assertTrue(exists);
    }

    @Test
    void existsByEmail() {
        String email = "emai@gmail.com";
        User entity = User.builder()
                .email(email)
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        userRepository.save(entity);
        boolean exists = userRepository.existsByEmail(email);
        assertTrue(exists);
    }
}
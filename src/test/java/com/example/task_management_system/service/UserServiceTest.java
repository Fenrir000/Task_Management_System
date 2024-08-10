package com.example.task_management_system.service;

import com.example.task_management_system.model.Role;
import com.example.task_management_system.model.User;
import com.example.task_management_system.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        User user = User.builder()
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("emai@gmail.com", savedUser.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void create() {
        User user = User.builder()
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername1")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);

        assertNotNull(createdUser);
        assertEquals("emai@gmail.com", createdUser.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void getByEmail() {
        User user = User.builder()
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        when(userRepository.findByEmail("emai@gmail.com")).thenReturn(Optional.of(user));

        User foundUser = userService.getByEmail("emai@gmail.com");

        assertNotNull(foundUser);
        assertEquals("emai@gmail.com", foundUser.getEmail());
        verify(userRepository).findByEmail("emai@gmail.com");
    }

    @Test
    void getById() {
        Long id = 1L;
        User user = User.builder()
                .id(1L)
                .email("emai@gmail.com")
                .role(Role.ROLE_USER)
                .assignedTasks(Collections.emptyList())
                .username("newUsername")
                .authorOf(Collections.emptyList())
                .password("newPassword")
                .build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User foundUser = userService.getById(id);

        assertNotNull(foundUser);
        assertEquals(id, foundUser.getId());
        verify(userRepository).findById(id);
    }
}
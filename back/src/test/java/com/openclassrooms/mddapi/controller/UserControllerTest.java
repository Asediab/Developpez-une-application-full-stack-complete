package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
    @MockBean
    private UserServiceImpl service;
    @MockBean
    private Authentication authentication;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    private User user;
    private UserDto userDto;
    private UserDetailsImpl userDetails;
    private UserUpdateDto userUpdateDto;


    @BeforeEach
    void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        userDto = new UserDto(1L, "email", "firstName", true, "password", new HashSet<SubjectDto>(), localDateTime, localDateTime);
        userUpdateDto = new UserUpdateDto(1L, "email", "firstName", true, "password", new HashSet<>() {
        }, localDateTime, localDateTime);
        user = new User(1L, "email", "firstName", "password", true, localDateTime
                , localDateTime, new HashSet<>() {
        });
        userDetails = new UserDetailsImpl(1L, "email", "firstName", "password", localDateTime, localDateTime);
    }

    @Test
    @DisplayName("Test FindById")
    void testFindById() {
        doReturn(new User()).when(service).getById(anyLong());

        ResponseEntity<?> response = userController.findById("1");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test FindById id isn't number")
    void testFindByIdIsNotNumber() {
        doReturn(new User()).when(service).getById(anyLong());

        ResponseEntity<?> response = userController.findById("notNumber");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Test FindById user Not Found")
    void testFindByIdUserNotFound() {
        doReturn(null).when(service).getById(anyLong());

        ResponseEntity<?> response = userController.findById("1");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Update")
    void testUpdate() {
        ResponseEntity<?> response2 = new ResponseEntity<>(user, HttpStatus.OK);
        doReturn(user).when(service).getById(anyLong());
        doReturn(response2).when(service).update(any(User.class));
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);

        ResponseEntity<?> response = userController.update(userUpdateDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Update UserUpdate is Null")
    void testUpdateUserUpdateIsNull() {
        ResponseEntity<?> response2 = new ResponseEntity<>(user, HttpStatus.OK);
        doReturn(null).when(service).getById(anyLong());
        doReturn(response2).when(service).update(any(User.class));
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);

        ResponseEntity<?> response = userController.update(userUpdateDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Delete")
    void testDelete() {
        doReturn(user).when(service).getById(anyLong());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
        doNothing().when(service).delete(any(User.class));

        ResponseEntity<?> response = userController.delete("1");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

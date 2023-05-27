package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserServiceImpl service;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtUtils jwtUtils;
    @MockBean
    private Authentication authentication;

    private SignupRequest signupRequest;
    private User user;
    private User updateUser;
    private UserDetailsImpl userDetails;

    @BeforeEach
    void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        signupRequest = new SignupRequest();
        signupRequest.setEmail("email");
        signupRequest.setPassword("pass");
        signupRequest.setFirstName("first");
        user = new User(1L, "email", "firstName", "password", true, localDateTime
                , localDateTime, new HashSet<>() {
        });
        updateUser = new User(1L, "emailUpd", "firstNameUpd", "password", true, localDateTime
                , localDateTime, new HashSet<>() {
        });
        userDetails = new UserDetailsImpl(1L, "email", "firstName", "password", localDateTime, localDateTime);
    }

    @Test
    @DisplayName("Test getById")
    void testGetById() {
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());

        User user1 = service.getById(1L);

        verify(userRepository, times(1)).findById(anyLong());
        Assertions.assertEquals(user, user1, "Sessions should de equals");
    }

    @Test
    @DisplayName("Test delete")
    void testDelete() {
        doNothing().when(userRepository).delete(any(User.class));
        doReturn(true).when(userRepository).existsByEmail(anyString());

        service.delete(user);

        verify(userRepository, times(1)).delete(any(User.class));
    }

    @Test
    @DisplayName("Test createUser")
    void testCreateUser() {
        doReturn(Optional.empty()).when(userRepository).findByEmail(anyString());
        doReturn(updateUser.getPassword()).when(encoder).encode(anyString());
        doReturn(updateUser).when(userRepository).save(any(User.class));

        User user1 = service.createUser(signupRequest);

        verify(userRepository, times(1)).save(any(User.class));
        Assertions.assertEquals(updateUser, user1, "Users should de equals");

    }

    @Test
    @DisplayName("Test update")
    void testUpdate() {
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(true).when(userRepository).existsByEmail(anyString());
        doReturn(updateUser.getPassword()).when(encoder).encode(anyString());
        doReturn(updateUser).when(userRepository).save(any(User.class));
        doReturn(authentication).when(authenticationManager).authenticate(any());
        doReturn(userDetails).when(authentication).getPrincipal();
        doReturn("token").when(jwtUtils).generateJwtToken(authentication);

        ResponseEntity<?> response = service.update(updateUser);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();

        assert jwtResponse != null;
        Assertions.assertNotNull(jwtResponse.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }
}

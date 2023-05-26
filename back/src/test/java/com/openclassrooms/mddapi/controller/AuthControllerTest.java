package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class AuthControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private Authentication authentication;

    @Autowired
    private AuthController authController;

    private SignupRequest signupRequest;
    private LoginRequest loginRequest;
    private User user;
    private UserDto userDto;
    private UserDetailsImpl userDetails;


    private final Long id = 1L;
    private final String email = "yoga@studio.com";
    private final String firstName = "Admin";
    private final String lastName = "Admin";
    private final String password = "test!1234";
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b2dhQHN0dWRpby5jb20iLCJpYXQiOjE2NzkzNDcxNDksImV4cCI" +
            "6MTY3OTQzMzU0OX0.LMLUeOwdXzY42N1AQRo2xPiBppbXmjSHDqS73EuxSb1zoOwjlRQiMt92COFPgb0_QbPk-nBCRDy075a9kA0Ptg";


    @BeforeEach
    void setup() {
        signupRequest = new SignupRequest();
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setFirstName(firstName);

        loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        userDto = new UserDto(id, email, firstName, true, password, new HashSet<SubjectDto>(), LocalDateTime.now(), LocalDateTime.now());
        user = new User(id, email, firstName, password, true, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>() {
        });
        userDetails = new UserDetailsImpl(id, email, firstName, password, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    @Disabled
    @DisplayName("Test authenticateUser")
    void testAuthenticateUser() {
        doReturn(authentication).when(authenticationManager).authenticate(any());
        doReturn(userDetails).when(authentication).getPrincipal();
        doReturn(token).when(jwtUtils).generateJwtToken(authentication);
        doReturn(Optional.of(user)).when(userRepository).findByEmail(any());

        ResponseEntity<?> response = authController.authenticateUser(loginRequest);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();

        assert jwtResponse != null;
        Assertions.assertNotNull(jwtResponse.getToken());
        Assertions.assertEquals(lastName, jwtResponse.getLastName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Disabled
    @DisplayName("Test Register user")
    void testRegisterUser() {
        doReturn(authentication).when(authenticationManager).authenticate(any());
        doReturn(userDetails).when(authentication).getPrincipal();
        doReturn(false).when(userRepository).existsByEmail(anyString());
        doAnswer(returnsFirstArg()).when(userRepository).save(any(User.class));
        doAnswer(returnsFirstArg()).when(passwordEncoder).encode(anyString());

        ResponseEntity<?> response = authController.registerUser(signupRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Disabled
    @DisplayName("Test Register user exist")
    void testRegisterUserExist() {
        doReturn(true).when(userRepository).existsByEmail(anyString());
        doAnswer(returnsFirstArg()).when(userRepository).save(any(User.class));
        doAnswer(returnsFirstArg()).when(passwordEncoder).encode(anyString());

        ResponseEntity<?> response = authController.registerUser(signupRequest);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Error: Email is already taken!", ((MessageResponse) Objects.requireNonNull(response.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test get curent user")
    void testGetMe() {
        doReturn(user).when(userService).getCurrentUser();

        ResponseEntity<?> response = authController.getMe();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
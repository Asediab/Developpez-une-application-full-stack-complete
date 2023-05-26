package com.openclassrooms.mddapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private static LoginRequest loginRequest;
    private static SignupRequest signupRequest;

    @BeforeAll
    static void init() {
        loginRequest = new LoginRequest();
        loginRequest.setEmail("yoga@studio.com");
        loginRequest.setPassword("1F4@sf5s6,18F4@sf5s6");
        signupRequest = new SignupRequest();
        signupRequest.setFirstName("UserF");
        signupRequest.setPassword("1F4@sf5s6,18F4@sf5s6");
        signupRequest.setEmail("user2023@user.com");
    }

    @Test
    @DisplayName("Test authenticateUser")
    void testAuthenticateUser() throws Exception {
        String json = objectMapper.writeValueAsString(loginRequest);

        MockHttpServletResponse result = mvc
                .perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()).andReturn().getResponse();

        String token = result.getContentAsString();
        Assertions.assertNotNull(token);
    }

    @Test
    @DisplayName("Test authenticateUser Bad credential")
    void testAuthenticateUserBedCredential() throws Exception {
        loginRequest.setPassword("00000");
        String json = objectMapper.writeValueAsString(loginRequest);

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    @DisplayName("Test registerUser")
    void registerUser() throws Exception {
        String json = objectMapper.writeValueAsString(signupRequest);

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test registerUser user exist")
    void registerUserExist() throws Exception {
        signupRequest.setEmail("yoga@studio.com");
        String json = objectMapper.writeValueAsString(signupRequest);

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @AfterAll
    void end() {
        String email = "user2023@user.com";
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email).get();
            userRepository.deleteById(user.getId());
        }
    }
}

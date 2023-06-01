package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "The Auth API. Contains all the operations " +
        "that can be performed a Auth.")
public class AuthController {
    private final UserService service;
    private final UserMapper mapper;

    public AuthController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * User login
     *
     * @param loginRequest User login data
     * @return The HTTP response with the Token
     */
    @Operation(summary = "Login an user")
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "LoginRequest form with Login and Password")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return service.createToken(loginRequest.getEmail(), loginRequest.getPassword());
    }

    /**
     * Register a new user
     *
     * @param signUpRequest The user credentials to register
     * @return The HTTP response with the Token
     */
    @Operation(summary = "Create new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "SignupRequest form with a new User data")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (service.userExistence(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        String password = signUpRequest.getPassword();
        service.createUser(signUpRequest);
        return service.createToken(signUpRequest.getEmail(), password);
    }

    /**
     * Get a curent authenticated user
     *
     * @return The HTTP response with UserDto credential
     */
    @Operation(summary = "Take a curent authenticated user")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        UserDto userDTO = this.mapper.toDto(service.getCurrentUser());

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: User not found"));
    }
}

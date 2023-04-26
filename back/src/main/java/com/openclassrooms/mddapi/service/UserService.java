package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User getById(Long userId);

    User createUser(SignupRequest signupRequest);

    void delete(User user);

    User update(User user);

    boolean userExistence(String email);

    User getCurrentUser();

    ResponseEntity<?> createToken(String mail, String password);
}

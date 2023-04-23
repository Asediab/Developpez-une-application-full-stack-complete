package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;

public interface UserService {

    User getById(Long userId);
    void delete(User user);
    User update(User user);
    boolean userExistence(String email);
}

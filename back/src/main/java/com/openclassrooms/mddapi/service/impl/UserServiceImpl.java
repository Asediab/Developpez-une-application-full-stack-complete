package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.exeption.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public void delete(User user) {
        if (userExistence(user.getEmail())) {
            this.userRepository.delete(user);
        }
        throw new NotFoundException();

    }

    @Override
    public User update(User user) {
        if (userExistence(user.getEmail())) {
            return this.userRepository.save(user);
        }
        throw new NotFoundException();
    }

    private boolean userExistence(String email){
        return this.userRepository.existsByEmail(email);
    }
}

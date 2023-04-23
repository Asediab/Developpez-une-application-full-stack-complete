package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.exeption.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public void delete(User user) {
        if (userExistence(user.getEmail())) {
            this.userRepository.delete(user);
        } else throw new NotFoundException("User isn't found");
    }

    // TODO Check a password changes
    @Override
    public User update(User user) {
        User oldUser = this.userRepository.findById(user.getId()).orElse(null);
        if (oldUser != null && userExistence(oldUser.getEmail())) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setCreatedAt(user.getCreatedAt());
            newUser.setUpdatedAt(LocalDateTime.now());
            return this.userRepository.save(user);
        } else throw new NotFoundException("User isn't found");
    }

    public boolean userExistence(String email) {
        return this.userRepository.existsByEmail(email);
    }
}

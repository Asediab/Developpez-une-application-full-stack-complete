package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.mapper.UserUpdateMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Log4j2
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final UserUpdateMapper updateMapper;

    public UserController(UserService service, UserMapper mapper, UserUpdateMapper updateMapper) {
        this.service = service;
        this.mapper = mapper;
        this.updateMapper = updateMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            User user = this.service.getById(Long.valueOf(id));
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(this.mapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            User user = this.service.getById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!Objects.equals(userDetails.getUsername(), user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            this.service.delete(user);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        User userOld = this.service.getById(userUpdateDto.getId());
        if (userOld == null) {
            return ResponseEntity.notFound().build();
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(userDetails.getUsername(), userOld.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return this.service.update(this.updateMapper.toEntity(userUpdateDto));
    }
}

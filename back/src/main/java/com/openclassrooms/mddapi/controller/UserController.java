package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Log4j2
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
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

            // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // if(!Objects.equals(userDetails.getUsername(), user.getEmail())) {
            //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            // }

            this.service.delete(user);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto) {
        User userOld = this.service.getById(userDto.getId());
        if (userOld == null) {
            return ResponseEntity.notFound().build();
        }
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // if(!Objects.equals(userDetails.getUsername(), user.getEmail())) {
        //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // }
        User userUpdated = this.service.update(this.mapper.toEntity(userDto));
        return ResponseEntity.ok().body(this.mapper.toDto(userUpdated));
    }
}

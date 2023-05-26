package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.mapper.MessageMapper;
import com.openclassrooms.mddapi.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
@Log4j2
@Tag(name = "Message", description = "The Message API. Contains all operations " +
        "that can be performed a Message.")
public class MessageController {
    private final MessageService service;
    private final MessageMapper mapper;

    public MessageController(MessageService service, MessageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Create a message")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto messageDto) {
        this.service.save(this.mapper.toEntity(messageDto));
        return ResponseEntity.ok().build();
    }
}

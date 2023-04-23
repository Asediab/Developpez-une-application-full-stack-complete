package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.mapper.MessageMapper;
import com.openclassrooms.mddapi.service.MessageService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
@Log4j2
public class MessageController {
    private final MessageService service;
    private final MessageMapper mapper;

    public MessageController(MessageService service, MessageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto messageDto) {
        log.info(messageDto);
        this.service.save(this.mapper.toEntity(messageDto));
        log.info(this.mapper.toEntity(messageDto));
        return ResponseEntity.ok().build();
    }
}

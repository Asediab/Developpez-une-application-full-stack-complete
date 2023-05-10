package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.service.SubscriptionService;
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
@RequestMapping("/api/subscription")
@Log4j2
public class SubscriptionController {
    private final SubscriptionService service;
    private final SubscriptionMapper mapper;

    public SubscriptionController(SubscriptionService service, SubscriptionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/sub")
    public ResponseEntity<?> subscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        log.info(subscriptionDto);
        this.service.subscribe(this.mapper.toEntity(subscriptionDto));
        log.info(this.mapper.toEntity(subscriptionDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unsub")
    public ResponseEntity<?> unsubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        log.info(subscriptionDto);
        this.service.unsubscribe(this.mapper.toEntity(subscriptionDto));
        return ResponseEntity.ok().build();
    }
}

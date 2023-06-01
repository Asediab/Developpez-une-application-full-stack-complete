package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.service.SubscriptionService;
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
@RequestMapping("/api/subscription")
@Log4j2
@Tag(name = "Subscription", description = "The Subscription API. Contains all the operations " +
        "that can be performed a Subscription.")
public class SubscriptionController {
    private final SubscriptionService service;
    private final SubscriptionMapper mapper;

    public SubscriptionController(SubscriptionService service, SubscriptionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Create a new subscription on a subject
     *
     * @param subscriptionDto credential for creating a new Subscription
     * @return The HTTP response
     */
    @Operation(summary = "Create a new subscription on a subject")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Object of SubscriptionDto.class")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/sub")
    public ResponseEntity<?> subscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        this.service.subscribe(this.mapper.toEntity(subscriptionDto));
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a subscription on a subject
     *
     * @param subscriptionDto credential for creating a new UnSubscription
     * @return The HTTP response
     */
    @Operation(summary = "Delete a subscription on a subject")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Object of SubscriptionDto.class")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/unsub")
    public ResponseEntity<?> unsubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        this.service.unsubscribe(this.mapper.toEntity(subscriptionDto));
        return ResponseEntity.ok().build();
    }
}

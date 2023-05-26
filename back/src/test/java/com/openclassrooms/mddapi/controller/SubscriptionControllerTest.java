package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.service.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class SubscriptionControllerTest {
    @MockBean
    private SubscriptionServiceImpl service;

    @Autowired
    private SubscriptionController subscriptionController;

    private Subscription subscription;

    @BeforeEach
    void init() {
        subscription = new Subscription(1L, 1L, 1L);
    }

    @Test
    @DisplayName("Test subscription")
    void testSubscription() {
        doNothing().when(service).subscribe(any());

        ResponseEntity<?> response = subscriptionController.subscription(new SubscriptionDto());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Unsubscription")
    void testUnSubscription() {
        doNothing().when(service).unsubscribe(any());

        ResponseEntity<?> response = subscriptionController.unsubscription(new SubscriptionDto());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

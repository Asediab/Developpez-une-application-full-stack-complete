package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class MessageControllerTest {
    @MockBean
    private MessageService service;

    @Autowired
    private MessageController messageController;

    private Message message = new Message();

    @Test
    @DisplayName("Test create message")
    void testCreate() {
        doReturn(message).when(service).save(any(Message.class));

        ResponseEntity<?> response = messageController.create(new MessageDto());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

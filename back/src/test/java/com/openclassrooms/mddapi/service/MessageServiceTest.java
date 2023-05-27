package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.repository.MessageRepository;
import com.openclassrooms.mddapi.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class MessageServiceTest {
    @Autowired
    MessageServiceImpl messageService;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    @DisplayName("Test Save Message")
    void testSave() {
        Message message = new Message();
        message.setId(1L);
        doReturn(message).when(messageRepository).save(any());

        Message returnedMessage = messageService.save(message);

        Assertions.assertNotNull(returnedMessage, "The saved session should not be null");
        Assertions.assertEquals(message, returnedMessage, "Sessions should de equals");
    }
}

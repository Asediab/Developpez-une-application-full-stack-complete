package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.repository.MessageRepository;
import com.openclassrooms.mddapi.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return this.messageRepository.save(message);
    }
}

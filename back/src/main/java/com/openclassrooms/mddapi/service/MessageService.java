package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Message;
import org.springframework.stereotype.Service;

public interface MessageService {
    Message save(Message message);
}

package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.model.Message;

public interface MessageService {
    Message save(Message message);
}

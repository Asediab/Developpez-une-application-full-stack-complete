package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;

public interface SubscriptionService {
    void subscribe(Long userId, Long subjectId);
    void unsubscribe(Long userId, Long subjectId);
}

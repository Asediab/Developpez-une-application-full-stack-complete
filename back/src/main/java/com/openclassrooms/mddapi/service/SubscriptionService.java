package com.openclassrooms.mddapi.service;

public interface SubscriptionService {
    void subscribe(Long userId, Long subjectId);
    void unsubscribe(Long userId, Long subjectId);
}

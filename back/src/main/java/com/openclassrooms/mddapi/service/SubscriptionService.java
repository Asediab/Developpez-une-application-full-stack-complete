package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;

public interface SubscriptionService {
    void subscribe(Subscription subscription);
    void unsubscribe(Subscription subscription);
}

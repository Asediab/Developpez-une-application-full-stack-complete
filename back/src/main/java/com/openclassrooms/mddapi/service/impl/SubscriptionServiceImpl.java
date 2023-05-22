package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.exeption.NotFoundException;
import com.openclassrooms.mddapi.exeption.ObjectExisteException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional
    public void subscribe(Subscription subscription) {
        if (!subscribeExistence(subscription)) {
            this.subscriptionRepository.save(subscription);
        } else throw new ObjectExisteException();
    }

    @Override
    @Transactional
    public void unsubscribe(Subscription subscription) {
        if (subscribeExistence(subscription)) {
            this.subscriptionRepository.deleteByUserIdAndSubjectId(subscription.getUserId(), subscription.getSubjectId());
        } else throw new NotFoundException("Subscription doesn't existe");
    }

    private boolean subscribeExistence(Subscription subscription) {
        return this.subscriptionRepository.existsByUserIdAndSubjectId(subscription.getUserId(), subscription.getSubjectId());
    }
}

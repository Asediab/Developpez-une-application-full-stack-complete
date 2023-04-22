package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.exeption.NotFoundException;
import com.openclassrooms.mddapi.exeption.ObjectExisteException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void subscribe(Long userId, Long subjectId) {
        if (!subscribeExistence(userId, subjectId)) {
            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setSubjectId(subjectId);
            this.subscriptionRepository.save(subscription);
        }
        throw new ObjectExisteException();
    }

    @Override
    public void unsubscribe(Long userId, Long subjectId) {
        if (subscribeExistence(userId, subjectId)) {
            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setSubjectId(subjectId);
            this.subscriptionRepository.save(subscription);
        }
        throw new NotFoundException();
    }

    private boolean subscribeExistence(Long userId, Long subjectId){
        return this.subscriptionRepository.existsByUserIdAndSubjectId(userId, subjectId);
    }
}

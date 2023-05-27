package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.service.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class SubscriptionServiceTest {
    @Autowired
    private SubscriptionServiceImpl service;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    private Subscription subscription = new Subscription(1L, 1L, 1L);

    @Test
    @DisplayName("Test subscribe")
    void testSubscribe() {
        doReturn(subscription).when(subscriptionRepository).save(any());
        doReturn(false).when(subscriptionRepository).existsByUserIdAndSubjectId(anyLong(), anyLong());

        service.subscribe(subscription);

        verify(subscriptionRepository, times(1)).save(any());
        verify(subscriptionRepository, times(1)).existsByUserIdAndSubjectId(anyLong(), anyLong());
    }

    @Test
    @DisplayName("Test unsubscribe")
    void testUnSubscribe() {
        doNothing().when(subscriptionRepository).deleteByUserIdAndSubjectId(anyLong(), anyLong());
        doReturn(true).when(subscriptionRepository).existsByUserIdAndSubjectId(anyLong(), anyLong());

        service.unsubscribe(subscription);

        verify(subscriptionRepository, times(1)).deleteByUserIdAndSubjectId(anyLong(), anyLong());
        verify(subscriptionRepository, times(1)).existsByUserIdAndSubjectId(anyLong(), anyLong());
    }
}

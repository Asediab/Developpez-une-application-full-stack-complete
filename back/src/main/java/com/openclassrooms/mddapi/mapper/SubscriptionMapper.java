package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subscription;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionDto toDto(Subscription subscription);
    Subscription toEntity(SubscriptionDto subscriptionDto);
}

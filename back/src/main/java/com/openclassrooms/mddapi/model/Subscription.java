package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "SUBSCRIPTIONS")
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NumberFormat
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @NumberFormat
    @Column(name = "subject_id")
    private Long subjectId;

}

package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

import java.util.Objects;

@Entity
@Table(name = "SUBSCRIPTIONS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(force = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, subjectId);
    }
}

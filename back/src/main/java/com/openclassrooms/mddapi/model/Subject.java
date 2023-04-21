package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Table(name = "SUBJECTS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "topic")
    @Size(max = 255)
    private String topic;

    @NotBlank
    @Column(name = "description", length = 2000)
    @Size(max = 255)
    private String description;

    @ManyToMany(mappedBy = "subjects")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject subject)) return false;
        return Objects.equals(id, subject.id) && Objects.equals(topic, subject.topic) && Objects.equals(description, subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, description);
    }
}

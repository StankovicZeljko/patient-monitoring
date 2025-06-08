package ch.stankovic.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String tenantId;

    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MotionEvent> motionEvents;
}

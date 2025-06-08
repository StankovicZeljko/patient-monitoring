package ch.stankovic.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String ward;

    @Column(nullable = false)
    private String tenantId;

    @OneToMany(mappedBy = "room")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MotionEvent> motionEvents;
}

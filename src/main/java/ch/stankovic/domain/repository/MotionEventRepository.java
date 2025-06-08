package ch.stankovic.domain.repository;

import ch.stankovic.domain.model.MotionEvent;
import ch.stankovic.domain.model.MotionType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.time.LocalDateTime;

public interface MotionEventRepository {

    Optional<MotionEvent> findById(UUID id, String tenantId);
    List<MotionEvent> findAllByTenant(String tenantId);
    List<MotionEvent> findByPatientAndPeriod(UUID patientId, LocalDateTime from, LocalDateTime to, String tenantId);
    List<MotionEvent> findRecentEventsByRoom(UUID roomId, String tenantId, int limit);
    List<MotionEvent> findEventsByTypeAndWard(MotionType type, String ward, String tenantId);
    Long countEventsByRoomAndPeriod(UUID roomId, LocalDateTime from, LocalDateTime to, String tenantId);
    void save(MotionEvent event);
    void deleteById(UUID id, String tenantId);
}

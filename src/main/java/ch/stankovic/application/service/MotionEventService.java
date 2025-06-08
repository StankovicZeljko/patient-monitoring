package ch.stankovic.application.service;

import ch.stankovic.domain.model.MotionEvent;
import ch.stankovic.domain.model.MotionType;
import jakarta.enterprise.context.ApplicationScoped;
import ch.stankovic.domain.repository.MotionEventRepository;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MotionEventService {

    private final MotionEventRepository motionEventRepository;

    @Inject
    public MotionEventService(MotionEventRepository motionEventRepository) {
        this.motionEventRepository = motionEventRepository;
    }

    public Optional<MotionEvent> getMotionEvent(UUID id, String tenantId) {
        return motionEventRepository.findById(id, tenantId);
    }

    public List<MotionEvent> getAllMotionEvents(String tenantId) {
        return motionEventRepository.findAllByTenant(tenantId);
    }

    public List<MotionEvent> getPatientEventsInPeriod(UUID patientId, LocalDateTime from, LocalDateTime to, String tenantId) {
        return motionEventRepository.findByPatientAndPeriod(patientId, from, to, tenantId);
    }

    public List<MotionEvent> getRecentRoomEvents(UUID roomId, String tenantId, int limit) {
        return motionEventRepository.findRecentEventsByRoom(roomId, tenantId, limit);
    }

    public List<MotionEvent> getEventsByTypeAndWard(MotionType type, String ward, String tenantId) {
        return motionEventRepository.findEventsByTypeAndWard(type, ward, tenantId);
    }

    public Long getRoomEventCount(UUID roomId, LocalDateTime from, LocalDateTime to, String tenantId) {
        return motionEventRepository.countEventsByRoomAndPeriod(roomId, from, to, tenantId);
    }

    public void createMotionEvent(MotionEvent event) {
        motionEventRepository.save(event);
    }

    public void deleteMotionEvent(UUID id, String tenantId) {
        motionEventRepository.deleteById(id, tenantId);
    }

}

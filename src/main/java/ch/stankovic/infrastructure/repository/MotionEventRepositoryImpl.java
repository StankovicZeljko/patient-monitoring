package ch.stankovic.infrastructure.repository;

import ch.stankovic.domain.model.MotionEvent;
import ch.stankovic.domain.repository.MotionEventRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MotionEventRepositoryImpl  implements PanacheRepository<MotionEvent>, MotionEventRepository {

    @Override
    public Optional<MotionEvent> findById(UUID id, String tenantId) {
        return find("id = ?1 and tenantId = ?2", id, tenantId).firstResultOptional();
    }

    @Override
    public List<MotionEvent> findAllByTenant(String tenantId) {
        return find("tenantId", tenantId).list();
    }

    @Override
    public List<MotionEvent> findByPatientAndPeriod(UUID patientId, LocalDateTime from, LocalDateTime to, String tenantId) {
        return find("""
            SELECT me FROM MotionEvent me 
            WHERE me.patient.id = ?1 
            AND me.timestamp BETWEEN ?2 AND ?3 
            AND me.tenantId = ?4 
            ORDER BY me.timestamp DESC
            """, patientId, from, to, tenantId).list();
    }

    @Override
    public void save(MotionEvent event) {
        persist(event);
    }

    @Override
    public void deleteById(UUID id, String tenantId) {
        delete("id = ?1 and tenantId = ?2", id, tenantId);
    }
}

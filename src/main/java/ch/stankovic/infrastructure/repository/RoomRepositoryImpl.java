package ch.stankovic.infrastructure.repository;

import ch.stankovic.domain.model.Room;
import ch.stankovic.domain.repository.RoomRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RoomRepositoryImpl implements PanacheRepository<Room>, RoomRepository {

    @Override
    public Optional<Room> findById(UUID id, String tenantId) {
        return find("id = ?1 and tenantId = ?2", id, tenantId).firstResultOptional();
    }

    @Override
    public List<Room> findAllByTenant(String tenantId) {
        return find("tenantId", tenantId).list();
    }

    @Override
    public List<Room> findByWard(String ward, String tenantId) {
        return find("ward = ?1 and tenantId = ?2", ward, tenantId).list();
    }

    @Override
    public Long countRoomsByWard(String ward, String tenantId) {
        return find("ward = ?1 and tenantId = ?2", ward, tenantId).count();
    }

    @Override
    public List<Room> findMostActiveRooms(String tenantId, int limit) {
        // Komplexe Aggregation: Räume nach Anzahl der MotionEvents sortiert
        return find("""
            SELECT r FROM Room r 
            LEFT JOIN r.motionEvents me 
            WHERE r.tenantId = ?1 
            GROUP BY r.id, r.roomNumber, r.ward, r.tenantId 
            ORDER BY COUNT(me) DESC
            """, tenantId).page(0, limit).list();
    }

    @Override
    public void save(Room room) {
        persist(room);
    }

    @Override
    public void deleteById(UUID id, String tenantId) {
        delete("id = ?1 and tenantId = ?2", id, tenantId);
    }
}

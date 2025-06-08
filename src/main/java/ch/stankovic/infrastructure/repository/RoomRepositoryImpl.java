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
    public void save(Room room) {
        persist(room);
    }

    @Override
    public void deleteById(UUID id, String tenantId) {
        delete("id = ?1 and tenantId = ?2", id, tenantId);
    }
}

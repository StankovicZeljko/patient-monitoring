package ch.stankovic.domain.repository;

import ch.stankovic.domain.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {

    Optional<Room> findById(UUID id, String tenantId);
    List<Room> findAllByTenant(String tenantId);
    List<Room> findByWard(String ward, String tenantId);
    Long countRoomsByWard(String ward, String tenantId);
    List<Room> findMostActiveRooms(String tenantId, int limit);
    void save(Room room);
    void deleteById(UUID id, String tenantId);
}

package ch.stankovic.application.service;

import ch.stankovic.domain.model.Room;
import ch.stankovic.domain.repository.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RoomService {

    private final RoomRepository roomRepository;

    @Inject
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<Room> getRoom(UUID id, String tenantId) {
        return roomRepository.findById(id, tenantId);
    }

    public List<Room> getAllRooms(String tenantId) {
        return roomRepository.findAllByTenant(tenantId);
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(UUID id, String tenantId) {
        roomRepository.deleteById(id, tenantId);
    }

}

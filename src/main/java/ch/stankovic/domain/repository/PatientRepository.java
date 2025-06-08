package ch.stankovic.domain.repository;

import ch.stankovic.domain.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {

    Optional<Patient> findById(UUID id, String tenantId);
    List<Patient> findAllByTenant(String tenantId);
    List<Patient> findPatientsByNamePattern(String namePattern, String tenantId);
    Long countPatientsByTenant(String tenantId);
    Long countMotionEventsByPatient(UUID patientId, String tenantId);
    void save(Patient patient);
    void deleteById(UUID id, String tenantId);
}

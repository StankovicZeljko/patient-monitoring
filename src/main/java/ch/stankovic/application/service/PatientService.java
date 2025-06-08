package ch.stankovic.application.service;

import ch.stankovic.domain.model.Patient;
import ch.stankovic.domain.repository.PatientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PatientService {

    private final PatientRepository patientRepository;

    @Inject
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> getPatient(UUID id, String tenantId) {
        return patientRepository.findById(id, tenantId);
    }

    public List<Patient> getAllPatients(String tenantId) {
        return patientRepository.findAllByTenant(tenantId);
    }

    public List<Patient> searchPatientsByName(String namePattern, String tenantId) {
        return patientRepository.findPatientsByNamePattern(namePattern, tenantId);
    }

    public Long getTotalPatientCount(String tenantId) {
        return patientRepository.countPatientsByTenant(tenantId);
    }

    public Long getPatientMotionEventCount(UUID patientId, String tenantId) {
        return patientRepository.countMotionEventsByPatient(patientId, tenantId);
    }

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(UUID id, String tenantId) {
        patientRepository.deleteById(id, tenantId);
    }
}

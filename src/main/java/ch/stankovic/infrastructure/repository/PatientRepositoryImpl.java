package ch.stankovic.infrastructure.repository;

import ch.stankovic.domain.model.Patient;
import ch.stankovic.domain.repository.PatientRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PatientRepositoryImpl implements PanacheRepository<Patient>, PatientRepository {


    @Override
    public Optional<Patient> findById(UUID id, String tenantId) {
        return find("id = ?1 and tenantId = ?2", id, tenantId).firstResultOptional();
    }

    @Override
    public List<Patient> findAllByTenant(String tenantId) {
        return find("tenantId", tenantId).list();
    }

    @Override
    public void save(Patient patient) {
        persist(patient);
    }

    @Override
    public void deleteById(UUID id, String tenantId) {
        delete("id = ?1 and tenantId = ?2", id, tenantId);
    }
}

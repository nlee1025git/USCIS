package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository {
    Beneficiary save(Beneficiary beneficiary);

    Optional<Beneficiary> findById(int id);

    Optional<Beneficiary> findByName(String lastName, String firstName, String middleName);

    List<Beneficiary> findAll();

    void remove(int id);

    Beneficiary update(Beneficiary beneficiary);

    List<Integer> findBeneficiaryIDs();
    List<Integer> findIdsByDateRange(LocalDate startDate, LocalDate endDate);
}

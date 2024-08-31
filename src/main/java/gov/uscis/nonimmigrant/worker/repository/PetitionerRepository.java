package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Petitioner;

import java.util.List;
import java.util.Optional;

public interface PetitionerRepository {
    Petitioner save(Petitioner petitioner);

    Optional<Petitioner> findById(int id);

    Optional<Petitioner> findByName(String lastName, String firstName, String middleName);

    List<Petitioner> findAll();

    void remove(int id);

    Petitioner update(Petitioner petitioner);

    Petitioner find(Petitioner petitioner);
}

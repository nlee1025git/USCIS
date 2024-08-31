package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class MemoryPetitionerRepository implements PetitionerRepository {
    private static Map<Integer, Petitioner> store = new HashMap<>();

    @Override
    public Petitioner save(Petitioner petitioner) {
//        petitioner.setId(id);
//        store.put(id, petitioner);
//        return petitioner;
        return null;
    }

    @Override
    public Optional<Petitioner> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Petitioner> findByName(String lastName, String firstName, String middleName) {
        return store.values().stream()
                .filter(member -> member.getLast().equals(lastName) && member.getFirst().equals(firstName) && member.getMiddle().equals(middleName))
                .findAny();
    }

    @Override
    public List<Petitioner> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Petitioner update(Petitioner petitioner) {
        return null;
    }

    @Override
    public Petitioner find(Petitioner petitioner) {
        return null;
    }
}

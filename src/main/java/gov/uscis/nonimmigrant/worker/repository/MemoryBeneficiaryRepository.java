package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class MemoryBeneficiaryRepository implements BeneficiaryRepository {
    private static Map<Integer, Beneficiary> store = new HashMap<>();
    private static int sequence = 0;

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
        beneficiary.setId(++sequence);
        store.put(beneficiary.getId(), beneficiary);
        return beneficiary;
    }

    @Override
    public Optional<Beneficiary> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Beneficiary> findByName(String lastName, String firstName, String middleName) {
        return store.values().stream()
                .filter(member -> member.getLastName().equals(lastName) && member.getFirstName().equals(firstName) && member.getMiddleName().equals(middleName))
                .findAny();
    }

    @Override
    public List<Beneficiary> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public Beneficiary update(Beneficiary beneficiary) {
        return null;
    }

    @Override
    public List<Integer> findBeneficiaryIDs() {
        return null;
    }

    @Override
    public List<Integer> findIdsByDateRange(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public void clearStore() {
        store.clear();
    }
}

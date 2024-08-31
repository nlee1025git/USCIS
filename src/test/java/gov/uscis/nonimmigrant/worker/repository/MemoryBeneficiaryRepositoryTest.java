package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryBeneficiaryRepositoryTest {
    MemoryBeneficiaryRepository repository = new MemoryBeneficiaryRepository();

    @AfterEach
    public  void afterEach() {
        repository.clearStore();
    }

//    @Test
//    public void save() {
//        Beneficiary beneficiary = new Beneficiary();
//        beneficiary.setLastName("John");
//        repository.save(beneficiary);
//
//        Beneficiary result = repository.findById(beneficiary.getId()).get();
//        assertEquals(beneficiary, result);
//    }
//
//    @Test
//    public void save2() {
//        Beneficiary beneficiary1 = new Beneficiary();
//        beneficiary1.setName("John");
//        repository.save(beneficiary1);
//
//        Beneficiary beneficiary2 = new Beneficiary();
//        beneficiary2.setName("James");
//        repository.save(beneficiary2);
//
//        Beneficiary result = repository.findById(beneficiary2.getId()).get();
//        assertEquals(beneficiary2.getId(), result.getId());
//    }
//
//    @Test
//    public void findByName() {
//        Beneficiary beneficiary1 = new Beneficiary();
//        beneficiary1.setName("John");
//        repository.save(beneficiary1);
//
//        Beneficiary beneficiary2 = new Beneficiary();
//        beneficiary2.setName("James");
//        repository.save(beneficiary2);
//
//        Beneficiary result = repository.findByName("James").get();
//        assertEquals(beneficiary2.getName(), result.getName());
//    }
//
//    @Test
//    public void findAll() {
//        Beneficiary beneficiary1 = new Beneficiary();
//        beneficiary1.setName("John");
//        repository.save(beneficiary1);
//
//        Beneficiary beneficiary2 = new Beneficiary();
//        beneficiary2.setName("James");
//        repository.save(beneficiary2);
//
//        Beneficiary beneficiary = new Beneficiary();
//        beneficiary.setName("Jane");
//        repository.save(beneficiary);
//
//        List<Beneficiary> result = repository.findAll();
//        assertEquals(result.size(), 3);
//    }
}

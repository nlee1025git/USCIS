package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import gov.uscis.nonimmigrant.worker.repository.BeneficiaryRepository;
import gov.uscis.nonimmigrant.worker.repository.PetitionerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class PetitionerServiceIntegrationTest {
    @Autowired BeneficiaryService beneficiaryService;
    @Autowired BeneficiaryRepository beneficiaryRepository;

    @Autowired PetitionerService petitionerService;
    @Autowired PetitionerRepository petitionerRepository;

    @Test
    void addExistPetitioner() {
        int id = 3;
        Petitioner petitioner = new Petitioner();
        petitioner.setId(id);
        petitioner.setLast("david");
        petitioner.setFirst("mason");
        petitioner.setMiddle("a");
        petitioner.setCompanyName("brownie");
        petitioner.setAddress("12345 penwith ct");
        petitioner.setNumber("");
        petitioner.setCity("abcde");
        petitioner.setState("md");
        petitioner.setZipcode("34567");
        petitioner.setPhoneNumber("111-222-3333");
        petitioner.setEmail("aaa@example.com");
        petitioner.setFein("p00");
        petitionerService.addData(petitioner, petitioner.getId());

        Petitioner findPetitioner = petitionerService.findPetitioner(id).get();
        System.out.println(petitioner.getId() + " " + findPetitioner.getId());

        assertThat(petitioner.getId()).isEqualTo(findPetitioner.getId());
        assertEquals(petitioner.getId(), findPetitioner.getId());
    }

    @Test
    void addNewPetitioner() {
        int id = 9;
        Petitioner petitioner = new Petitioner();
        petitioner.setId(id);
        petitioner.setLast("david");
        petitioner.setFirst("mason");
        petitioner.setMiddle("a");
        petitioner.setCompanyName("brownie");
        petitioner.setAddress("12345 penwith ct");
        petitioner.setNumber("");
        petitioner.setCity("abcde");
        petitioner.setState("md");
        petitioner.setZipcode("34567");
        petitioner.setPhoneNumber("111-222-3333");
        petitioner.setEmail("aaa@example.com");
        petitioner.setFein("p00");
        petitionerService.addData(petitioner, petitioner.getId());

        Petitioner findPetitioner = petitionerService.findPetitioner(id).get();
        System.out.println(petitioner.getId() + " " + findPetitioner.getId());

        assertThat(petitioner.getId()).isEqualTo(findPetitioner.getId());
        assertEquals(petitioner.getId(), findPetitioner.getId());
    }

    @Test
    void showPetitioners() {
        List<Petitioner> petitionerList = petitionerService.findPetitioners();
        assertThat(petitionerList.size()).isEqualTo(2);
    }

    @Test
    void findPetitioner() {
        int id = 2;
        Optional<Petitioner> petitioner = petitionerService.findPetitioner(id);
        Petitioner petitioner1 = petitioner.get();
        assertEquals(id, petitioner1.getId());
    }
}
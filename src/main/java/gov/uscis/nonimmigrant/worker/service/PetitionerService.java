package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import gov.uscis.nonimmigrant.worker.repository.PetitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionerService {
    private final PetitionerRepository petitionerRepository;

    @Autowired
    public PetitionerService(PetitionerRepository petitionerRepository) {
        this.petitionerRepository = petitionerRepository;
    }

    public void addData(Petitioner petitioner) {
        petitionerRepository.save(petitioner);
    }

    public void deleteData(Petitioner petitioner) {
//        int id = findBeneficiary(beneficiary.getId()).get().getId();
//        beneficiaryRepository.remove(id);
    }

//    private List<Petitioner> validateDuplicateId(Petitioner petitioner) {
//        beneficiaryRepository.findById(beneficiary.getId())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("Beneficiary already exists");
//                });
//    }

    public List<Petitioner> findPetitioners() {
        return petitionerRepository.findAll();
    }

    public Optional<Petitioner> findPetitioner(int id) {
        return petitionerRepository.findById(id);
    }

    public void updatePetitioner(int id, String lastName, String firstName, String middleName, String companyName, String address,
                                 String number, String city, String state, String zipcode, String phoneNumber, String email, String fein) {
        Petitioner petitioner = petitionerRepository.findById(id).orElse(null);

        if (petitioner != null) {
            petitioner.setId(id);
            petitioner.setLast(lastName);
            petitioner.setFirst(firstName);
            petitioner.setMiddle(middleName);
            petitioner.setCompanyName(companyName);
            petitioner.setAddress(address);
            petitioner.setNumber(number);
            petitioner.setCity(city);
            petitioner.setState(state);
            petitioner.setZipcode(zipcode);
            petitioner.setPhoneNumber(phoneNumber);
            petitioner.setEmail(email);
            petitioner.setFein(fein);
        }
        petitionerRepository.update(petitioner);
    }

    public Petitioner findOrCreate(Petitioner petitioner) {
        Petitioner existingPetitioner = petitionerRepository.find(petitioner);
        return existingPetitioner;
    }
}

package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;
import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import gov.uscis.nonimmigrant.worker.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    public void addData(Beneficiary beneficiary) {
        validateDuplicateId(beneficiary);
//        validateDuplicateName(beneficiary);
        beneficiaryRepository.findByName(beneficiary.getLastName(), beneficiary.getFirstName(), beneficiary.getMiddleName())
                .ifPresentOrElse(m -> {
                    System.out.println("already exists");
                }, () -> {
                    System.out.println("not exists");
                });
        beneficiaryRepository.save(beneficiary);
    }

    private void validateDuplicateName(Beneficiary beneficiary) {
        beneficiaryRepository.findByName(beneficiary.getLastName(), beneficiary.getFirstName(), beneficiary.getMiddleName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Beneficiary name already exists");
                });
    }

    public void deleteData(Beneficiary beneficiary) {
        int id = findBeneficiary(beneficiary.getId()).get().getId();
        beneficiaryRepository.remove(id);
    }

    private void validateDuplicateId(Beneficiary beneficiary) {
        beneficiaryRepository.findById(beneficiary.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("Beneficiary ID already exists");
                });
    }

    public List<Beneficiary> findBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    public Optional<Beneficiary> findBeneficiary(int id) {
        return beneficiaryRepository.findById(id);
    }

    public void updateBeneficiary(int id, String lastName, String firstName, String middleName, Date dob, String gender, String country, String passportNumber, String education) {
        Beneficiary beneficiary = beneficiaryRepository.findById(id).orElse(null);
        if (beneficiary != null) {
            beneficiary.setLastName(lastName);
            beneficiary.setFirstName(firstName);
            beneficiary.setMiddleName(middleName);
            beneficiary.setDOB(dob);
            beneficiary.setGender(gender);
            beneficiary.setCountry(country);
            beneficiary.setPassportNumber(passportNumber);
            beneficiary.setEducation(education);
        }
        beneficiaryRepository.update(beneficiary);
    }

    public List<Integer> findBeneficiaryIDs() {
        return beneficiaryRepository.findBeneficiaryIDs();
    }

    public List<Integer> findIdsInRange(LocalDate startDate, LocalDate endDate) {
        return beneficiaryRepository.findIdsByDateRange(startDate, endDate);
    }



/* Scenario:
 * Employer (Petitioner): XYZ Tech Solutions Inc.
 * Employee (Beneficiary): John Doe, a software engineer from India
 * Visa Type: H-1B visa, which is for temporary workers in speciality occupations
 *
 * 1. Petitioner Verification:
 *   • USCIS verifies XYZ Tech Solutions Inc. as a legitimate business entity registered in VA.
 *   • Checks the financial stability of XYZ Tech Solutions Inc. to ensure it can support the H-1B employment.
 *
 * 2. Job Offer and Position Verification:
 *
 * 3. Beneficiary's Qualifications:
 *
 * 4. Criminal History and Security Checks:
 *
 * 5. Documentation Review:
 *
 * 6. Employment and Wage Compliance:
 *
 * 7. Consistency and Truthfulness:
 *
 * 8. Admissibility Criteria:
 */



    public boolean meetUSCISRequirements(Beneficiary beneficiary, Petitioner petitioner, BackgroundCheck backgroundCheck, String minimumDegree) {
        return isValidEmployer(petitioner) && hasRequiredEducation("A", minimumDegree) &&
               hasValidEmployment(beneficiary) && hasNoCriminalRecords(beneficiary) && !isFromCountry(beneficiary.getCountry());
    }

    public boolean isValidEmployer(Petitioner petitioner) {
        String[] companies = {"A Company", "B Company", "C Company"};
        for (String company : companies) {
            if (company.equalsIgnoreCase(petitioner.getCompanyName())) {
                return true;
            }
        }
        return false;
    }

    public enum degreeType {Diploma, Associate, Bachelor, Master, Doctorate}

    private degreeType getDegree(String degree) {
        switch (degree) {
            case "Diploma":
                return degreeType.Diploma;
            case "Associate":
                return degreeType.Associate;
            case "Bachelor":
                return degreeType.Bachelor;
            case "Master":
                return degreeType.Master;
            case "Doctorate":
                return degreeType.Doctorate;
            default:
                throw new IllegalArgumentException("Not valid degree");
        }
    }
    public boolean hasRequiredEducation(String degree, String minimumDegree) {
        degreeType given = getDegree(degree);
        degreeType minimum = getDegree(minimumDegree);
        System.out.println(given.compareTo(minimum));
        return given.compareTo(minimum) >= 0;

//        List<String> degreeTypes = Arrays.asList("Diploma", "Associate", "Bachelor", "Master", "Doctorate");
//        return degreeTypes.stream()
//                .anyMatch(type -> type.equalsIgnoreCase(degree));
    }

    public boolean hasValidEmployment(Beneficiary beneficiary) {
        boolean hasEmployment = false;
        String[] employmentHistory = {"A Company", "B Company"};
        if (employmentHistory.length < beneficiary.getId()) {
            return false;
        }
        return true;
    }

    public boolean hasNoCriminalRecords(Beneficiary beneficiary) {
        boolean hasFelony = false;
        String[] felonies = {"Fraud", "Drug"};
        for (String felony : felonies) {
            if (felony.equalsIgnoreCase(beneficiary.getFirstName())) {
                return false;
            }
        }
        return true;
    }

    public boolean isFromCountry(String country) {
        String[] dangerousCountries = {"Iran", "China", "Russia", "North Korea", "Pakistan", "Afghanistan", "Syria", "Iraq", "Venezuela", "Somalia"};
        for (int i = 0; i < dangerousCountries.length; i++) {
            if (dangerousCountries[i].equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }
}

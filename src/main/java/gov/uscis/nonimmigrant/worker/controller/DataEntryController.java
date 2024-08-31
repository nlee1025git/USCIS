package gov.uscis.nonimmigrant.worker.controller;

import gov.uscis.nonimmigrant.worker.domain.*;
import gov.uscis.nonimmigrant.worker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DataEntryController {
    private final BeneficiaryService beneficiaryService;
    private final PetitionerService petitionerService;
    private final CountryService countryService;
    private final VisaApplicationService visaApplicationService;
    private final BackgroundCheckService backgroundCheckService;

    @Autowired
    public DataEntryController(BeneficiaryService beneficiaryService, PetitionerService petitionerService, CountryService countryService,
                               VisaApplicationService visaApplicationService, BackgroundCheckService backgroundCheckService) {
        this.beneficiaryService = beneficiaryService;
        this.petitionerService = petitionerService;
        this.countryService = countryService;
        this.visaApplicationService = visaApplicationService;
        this.backgroundCheckService = backgroundCheckService;
    }

    @GetMapping("data-entry") // look up url
    public String createForm(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);

        List<Country> countries = countryService.findCountries()
                .stream()
                .sorted(Comparator.comparing(Country::getCountry_name))
                .collect(Collectors.toList());
        model.addAttribute("countries", countries);
        return "dataEntry";
    }

    @PostMapping("data-entry") // deliever data form
    public String create(Beneficiary beneficiary, Petitioner petitioner, VisaApplication visaApplication, @RequestParam("dob") Date dob, @RequestParam("job") String jobTitle,
                         @RequestParam("from") Date fromDate, @RequestParam("to") Date toDate, @RequestParam("visa") String visaType) {
        boolean initialize = false;
        if (initialize) {
            initializer();
            return "redirect:/data-entry";
        }

        beneficiary.setDOB(dob);
        visaApplication.setStatus("1");
        visaApplication.setJobTitle(jobTitle);
        visaApplication.setFromDate(fromDate);
        visaApplication.setToDate(toDate);
        visaApplication.setVisaType(visaType);
        beneficiaryService.addData(beneficiary);
        Petitioner existingPetitioner = petitionerService.findOrCreate(petitioner);
        if (existingPetitioner == null) {
            petitionerService.addData(petitioner);
            visaApplicationService.addApplication(visaApplication, beneficiary.getId(), petitioner.getId());
        } else {
            visaApplicationService.addApplication(visaApplication, beneficiary.getId(), existingPetitioner.getId());
        }
        backgroundCheckService.addBackground(visaApplication.getApplicationId());
        return "redirect:/data-entry";
    }

    // manually enter the initial data
    private void initializer() {
        LocalDate today = LocalDate.now();
        String[] lastNames = {"Johnson", "Rodriguez", "Thompson", "Martinez", "Davis", "Garcia", "Wilson", "Wilson", "Lopez", "Robinson", "Hernandez", "Lee", "Taylor", "Moore", "Lewis", "Clark", "Adams", "Scott", "King", "Green", "Baker"};
        String[] firstNames = {"Alexander", "Emily", "Benjamin", "Sophia", "William", "Olivia", "James", "Isabella", "Michael", "Mia", "Christopher", "Charlotte", "Matthew", "Amelia", "Andrew", "Evelyn", "Joshua", "Harper", "Daniel", "Grace"};
        String[] middleNames = {"M", "R", "J", "L", "H", "A", "T", "C", "S", "E", "A", "M", "D", "J", "P", "K", "B", "L", "R", "N"};
        String[] companyNames = {"A Company", "B Company", "C Company", "D Company", "E Company"};
        String[] addresses = {"123 Main St.", "456 Main St.", "789 Main St.", "234 Main St.", "345 Main St."};
        String[] numbers = {"1", "2", "3", "4", "5"};
        String city = "Fairfax";
        String state = "VA";
        String zipcode = "22032";
        String[] phoneNumbers = {"(703) 923-3009", "(703) 334-9900", "(703) 345-2121", "(703) 999-7878", "(703) 923-5839"};
        String[] emails = {"a@example.com", "b@example.com", "d@example.com", "d@example.com", "e@example.com"};
        String[] feins = {"00001", "00002", "00003", "00004", "00005"};

        Date[] dates = {Date.valueOf("1999-01-19"), Date.valueOf("2002-08-22")};
        String[] genders = {"m", "f"};
        String[] countries = {"Albania", "Algeria", "Angola", "Andorra"};
        String[] passportNumbers = {"A00001", "A00002", "A00003", "A00004", "A00005", "A00006", "A00007", "A00008", "A00009", "A00010"};
        String[] educations = {"Diploma", "Associate", "Bachelor", "Master", "Doctorate"};

        Date[] applicationDates = {Date.valueOf("2000-11-12"), Date.valueOf("2001-09-10"), Date.valueOf("2003-01-03")};
        String[] jobTitles = {"Software Engineer", "Software Developer"};
        String[] wages = {"80000", "70000"};
        Date[] fromDates = {Date.valueOf("2004-01-02"), Date.valueOf("1999-02-03")};
        Date[] toDates = {Date.valueOf("2005-01-02"), Date.valueOf("2005-07-05")};
        String[] visaTypes = {"h-1b", "h-2b"};

        String[] results = {"Pass", "Fail"};
        Date[] checkDates = {Date.valueOf("2024-07-11"), Date.valueOf("2024-07-15")};

        int offset = 10;
        for (int i = 0; i < 4; i++) {
            Petitioner tempPetitioner = new Petitioner();
            tempPetitioner.setId((i + 1));
            tempPetitioner.setLast(lastNames[i]);
            tempPetitioner.setFirst(firstNames[i]);
            tempPetitioner.setMiddle(middleNames[i]);
            tempPetitioner.setCompanyName(companyNames[i % companyNames.length]);
            tempPetitioner.setAddress(addresses[i % addresses.length]);
            tempPetitioner.setNumber(numbers[i % numbers.length]);
            tempPetitioner.setCity(city);
            tempPetitioner.setState(state);
            tempPetitioner.setZipcode(zipcode);
            tempPetitioner.setPhoneNumber(phoneNumbers[i % phoneNumbers.length]);
            tempPetitioner.setEmail(emails[i % emails.length]);
            tempPetitioner.setFein(feins[i % feins.length]);

            Beneficiary tempBeneficiary = new Beneficiary();
            tempBeneficiary.setId((i + 1));
            tempBeneficiary.setLastName(lastNames[i + offset]);
            tempBeneficiary.setFirstName(firstNames[i + offset]);
            tempBeneficiary.setMiddleName(middleNames[i + offset]);
            tempBeneficiary.setDOB(dates[i % dates.length]);
            tempBeneficiary.setGender(genders[i % genders.length]);
            tempBeneficiary.setCountry(countries[i % countries.length]);
            tempBeneficiary.setPassportNumber(passportNumbers[i % passportNumbers.length]);
            tempBeneficiary.setEducation(educations[i % educations.length]);

            VisaApplication tempVisaApplication = new VisaApplication();
            tempVisaApplication.setApplicationId((i + 1));
            tempVisaApplication.setPetitionerId(tempPetitioner.getId());
            tempVisaApplication.setBeneficiaryId(tempBeneficiary.getId());
            tempVisaApplication.setStatus("1");
            tempVisaApplication.setApplicationDate(applicationDates[i % applicationDates.length]);
            tempVisaApplication.setApprovalDate(null);
            tempVisaApplication.setJobTitle(jobTitles[i % jobTitles.length]);
            tempVisaApplication.setWage(wages[i % wages.length]);
            tempVisaApplication.setFromDate(fromDates[i % fromDates.length]);
            tempVisaApplication.setToDate(toDates[i % toDates.length]);
            tempVisaApplication.setVisaType(visaTypes[i % visaTypes.length]);

            int range = 10;
            BackgroundCheck tempBackgroundCheck = new BackgroundCheck();
            tempBackgroundCheck.setBackgroundId((i + 1));
            tempBackgroundCheck.setApplicationId(tempVisaApplication.getApplicationId());
            tempBackgroundCheck.setJobTitleVerification((int) (Math.random() * range) + 1);
            tempBackgroundCheck.setWageCompliance((int) (Math.random() * range) + 1);
            tempBackgroundCheck.setBeneficiaryQualification((int) (Math.random() * range) + 1);
            tempBackgroundCheck.setCriminalRecord((int) (Math.random() * range) + 1);
            tempBackgroundCheck.setResult(results[i % results.length]);
            tempBackgroundCheck.setCheckDate(checkDates[i % checkDates.length]);

            beneficiaryService.addData(tempBeneficiary);
            petitionerService.addData(tempPetitioner);
            visaApplicationService.addApplication(tempVisaApplication, tempVisaApplication.getBeneficiaryId(), tempVisaApplication.getPetitionerId());
            backgroundCheckService.addBackground(tempBackgroundCheck);
        }
    }
}

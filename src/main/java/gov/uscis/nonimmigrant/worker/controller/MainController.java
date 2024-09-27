package gov.uscis.nonimmigrant.worker.controller;

import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;
import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import gov.uscis.nonimmigrant.worker.domain.VisaApplication;
import gov.uscis.nonimmigrant.worker.service.BackgroundCheckService;
import gov.uscis.nonimmigrant.worker.service.BeneficiaryService;
import gov.uscis.nonimmigrant.worker.service.PetitionerService;
import gov.uscis.nonimmigrant.worker.service.VisaApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
public class MainController {
    private final PetitionerService petitionerService;
    private final BeneficiaryService beneficiaryService;
    private final VisaApplicationService visaApplicationService;
    private final BackgroundCheckService backgroundCheckService;

    public MainController(PetitionerService petitionerService, BeneficiaryService beneficiaryService, VisaApplicationService visaApplicationService, BackgroundCheckService backgroundCheckService) {
        this.petitionerService = petitionerService;
        this.beneficiaryService = beneficiaryService;
        this.visaApplicationService = visaApplicationService;
        this.backgroundCheckService = backgroundCheckService;
    }

    @GetMapping("/")
    public String mainScreen() {
//        initData();
        return "mainScreen";
    }

    private void initData() {
        ReadController readController = new ReadController();
        List<List<String>> data = readController.getData();

        for (int i = 0; i < data.size(); i++) {
            List<Petitioner> petitionerList = petitionerService.findPetitioners();
            List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
            List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
            List<BackgroundCheck> backgroundCheckList = backgroundCheckService.findBackgrounds();
            List<String> temp = data.get(i);
            int index = 0;

            Petitioner petitioner = new Petitioner();
            petitioner.setId(petitionerList.size() + 1);
            petitioner.setLast(temp.get(index++));
            petitioner.setFirst(temp.get(index++));
            petitioner.setMiddle(temp.get(index++));
            petitioner.setCompanyName(temp.get(index++));
            petitioner.setAddress(temp.get(index++));
            petitioner.setNumber(temp.get(index++));
            petitioner.setCity(temp.get(index++));
            petitioner.setState(temp.get(index++));
            petitioner.setZipcode(temp.get(index++));
            petitioner.setPhoneNumber(temp.get(index++));
            petitioner.setEmail(temp.get(index++));
            petitioner.setFein(temp.get(index++));

            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setId(beneficiaryList.size() + 1);
            beneficiary.setLastName(temp.get(index++));
            beneficiary.setFirstName(temp.get(index++));
            beneficiary.setMiddleName(temp.get(index++));
//            String beneficiaryDate = getDate(temp, index++);
//            beneficiary.setDOB(Date.valueOf(beneficiaryDate));
            beneficiary.setDOB(Date.valueOf(temp.get(index++)));
            beneficiary.setGender(temp.get(index++));
            beneficiary.setCountry(temp.get(index++));
            beneficiary.setPassportNumber(temp.get(index++));
            beneficiary.setEducation(temp.get(index++));

            VisaApplication visaApplication = new VisaApplication();
            visaApplication.setApplicationId(visaApplicationList.size() + 1);
            visaApplication.setPetitionerId(petitioner.getId());
            visaApplication.setBeneficiaryId(beneficiary.getId());
            visaApplication.setStatus("1");
            LocalDate today = LocalDate.now();
            Date date = Date.valueOf(today);
            visaApplication.setApplicationDate(date);
            visaApplication.setApprovalDate(null);
            visaApplication.setJobTitle(temp.get(index++));
            visaApplication.setWage(temp.get(index++));
//            String fromDate = getDate(temp, index++);
//            visaApplication.setFromDate(Date.valueOf(fromDate));
            visaApplication.setFromDate(Date.valueOf(temp.get(index++)));
//            String toDate = getDate(temp, index++);
//            visaApplication.setToDate(Date.valueOf(toDate));
            visaApplication.setToDate(Date.valueOf(temp.get(index++)));
            visaApplication.setVisaType(temp.get(index++));

            int range = 10;
            BackgroundCheck backgroundCheck = new BackgroundCheck();
            backgroundCheck.setBackgroundId(backgroundCheckList.size() + 1);
            backgroundCheck.setApplicationId(visaApplication.getApplicationId());
            backgroundCheck.setJobTitleVerification((int) (Math.random() * range) + 1);
            backgroundCheck.setWageCompliance((int) (Math.random() * range) + 1);
            backgroundCheck.setBeneficiaryQualification((int) (Math.random() * range) + 1);
            backgroundCheck.setCriminalRecord((int) (Math.random() * range) + 1);
            backgroundCheck.setResult(null);
            backgroundCheck.setCheckDate(null);

            petitionerService.addData(petitioner);
            beneficiaryService.addData(beneficiary);
            visaApplicationService.addApplication(visaApplication, visaApplication.getBeneficiaryId(), visaApplication.getPetitionerId());
            backgroundCheckService.addBackground(backgroundCheck);
        }
    }

    private static String getDate(List<String> temp, int index) {
        String format = "";
        String inputDate = temp.get(index);
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = inputDateFormat.parse(inputDate);
            Date sqlDate = new Date(date.getTime());
            format = outputDateFormat.format(sqlDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }
}
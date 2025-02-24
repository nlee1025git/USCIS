package gov.uscis.nonimmigrant.worker.controller;
 
import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;
import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import gov.uscis.nonimmigrant.worker.domain.VisaApplication;
import gov.uscis.nonimmigrant.worker.form.DateForm;
import gov.uscis.nonimmigrant.worker.service.BackgroundCheckService;
import gov.uscis.nonimmigrant.worker.service.BeneficiaryService;
import gov.uscis.nonimmigrant.worker.service.PetitionerService;
import gov.uscis.nonimmigrant.worker.service.VisaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApprovalController {
    private final BeneficiaryService beneficiaryService;
    private final PetitionerService petitionerService;
    private final VisaApplicationService visaApplicationService;
    private final BackgroundCheckService backgroundCheckService;

    @Autowired
    public ApprovalController(BeneficiaryService beneficiaryService, PetitionerService petitionerService, VisaApplicationService visaApplicationService, BackgroundCheckService backgroundCheckService) {
        this.beneficiaryService = beneficiaryService;
        this.petitionerService = petitionerService;
        this.visaApplicationService = visaApplicationService;
        this.backgroundCheckService = backgroundCheckService;
    }

    public boolean validateLogin(String username, String password) {
        return username.equals("admin") && password.equals("a");
    }

    @GetMapping("approveLogin")
    public String approveLogin() {
        return "approveLogin";
    }

    @GetMapping("approveID")
    public String approveID(@RequestParam("id") int id, Model model) {
        VisaApplication visaApplication = setApplication(id);
        model.addAttribute("visaApplication", visaApplication);
        setBeneficiary(visaApplication.getBeneficiaryId(), model);
        setPetitioner(visaApplication.getPetitionerId(), model);
        setBackgroundCheck(id, model);
        return "approve";
    }

    @PostMapping("/approveSearch")
    public String searchID(Model model) {
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        model.addAttribute("beneficiaryList", beneficiaryList);
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        model.addAttribute("visaApplicationList", visaApplicationList);

        List<VisaApplication> pendingList = new ArrayList<>();
        List<Beneficiary> pendingList2 = new ArrayList<>();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (visaApplicationList.get(i).getStatus().equals("2")) {
                pendingList.add(visaApplicationList.get(i));
                int id = visaApplicationList.get(i).getBeneficiaryId();
                for (int j = 0; j < beneficiaryList.size(); j++) {
                    if (beneficiaryList.get(j).getId() == id) {
                        pendingList2.add(beneficiaryList.get(j));
                        break;
                    }
                }
            }
        }
        model.addAttribute("pendingList", pendingList);
        model.addAttribute("pendingList2", pendingList2);
        return "approveID";
    }

    private void setBeneficiary(int id, Model model) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        for (int i = 0; i < beneficiaryList.size(); i++) {
            if (beneficiaryList.get(i).getId() == id) {
                setBeneficiary(id, beneficiary, beneficiaryList, i);
                break;
            }
        }
        model.addAttribute("beneficiary", beneficiary);
    }

    private static void setBeneficiary(int id, Beneficiary beneficiary, List<Beneficiary> beneficiaryList, int i) {
        beneficiary.setId(id);
        beneficiary.setLastName(beneficiaryList.get(i).getLastName());
        beneficiary.setFirstName(beneficiaryList.get(i).getFirstName());
        beneficiary.setMiddleName(beneficiaryList.get(i).getMiddleName());
        beneficiary.setDOB(beneficiaryList.get(i).getDOB());
        beneficiary.setGender(beneficiaryList.get(i).getGender());
        beneficiary.setCountry(beneficiaryList.get(i).getCountry());
        beneficiary.setPassportNumber(beneficiaryList.get(i).getPassportNumber());
        beneficiary.setEducation(beneficiaryList.get(i).getEducation());
    }

    private void setPetitioner(int id, Model model) {
        Petitioner petitioner = new Petitioner();
        List<Petitioner> petitionerList = petitionerService.findPetitioners();
        for (int i = 0; i < petitionerList.size(); i++) {
            if (petitionerList.get(i).getId() == id) {
                petitioner.setId(id);
                petitioner.setLast(petitionerList.get(i).getLast());
                petitioner.setFirst(petitionerList.get(i).getFirst());
                petitioner.setMiddle(petitionerList.get(i).getMiddle());
                petitioner.setCompanyName(petitionerList.get(i).getCompanyName());
                petitioner.setAddress(petitionerList.get(i).getAddress());
                petitioner.setNumber(petitionerList.get(i).getNumber());
                petitioner.setCity(petitionerList.get(i).getCity());
                petitioner.setState(petitionerList.get(i).getState());
                petitioner.setZipcode(petitionerList.get(i).getZipcode());
                petitioner.setPhoneNumber(petitionerList.get(i).getPhoneNumber());
                petitioner.setEmail(petitionerList.get(i).getEmail());
                petitioner.setFein(petitionerList.get(i).getFein());
            }
        }
        model.addAttribute("petitioner", petitioner);
    }

    private VisaApplication setApplication(int id) {
        VisaApplication visaApplication = new VisaApplication();
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            VisaApplication temp = visaApplicationList.get(i);
            if (temp.getApplicationId() == id) {
                visaApplication.setApplicationId(id);
                visaApplication.setBeneficiaryId(temp.getBeneficiaryId());
                visaApplication.setPetitionerId(temp.getPetitionerId());
                visaApplication.setStatus(temp.getStatus());
                visaApplication.setApplicationDate(temp.getApplicationDate());
                visaApplication.setApprovalDate(temp.getApprovalDate());
                visaApplication.setJobTitle(temp.getJobTitle());
                visaApplication.setWage(temp.getWage());
                visaApplication.setFromDate(temp.getFromDate());
                visaApplication.setToDate(temp.getToDate());
                visaApplication.setVisaType(temp.getVisaType());
            }
        }
        return visaApplication;
    }

    private void setBackgroundCheck(int id, Model model) {
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        List<BackgroundCheck> backgroundCheckList = backgroundCheckService.findBackgrounds();
        for (int i = 0; i < backgroundCheckList.size(); i++) {
            if (backgroundCheckList.get(i).getApplicationId() == id) {
                backgroundCheck.setBackgroundId(backgroundCheckList.get(i).getBackgroundId());
                backgroundCheck.setApplicationId(id);
                backgroundCheck.setJobTitleVerification(backgroundCheckList.get(i).getJobTitleVerification());
                backgroundCheck.setWageCompliance(backgroundCheckList.get(i).getWageCompliance());
                backgroundCheck.setBeneficiaryQualification(backgroundCheckList.get(i).getBeneficiaryQualification());
                backgroundCheck.setCriminalRecord(backgroundCheckList.get(i).getCriminalRecord());
                backgroundCheck.setResult(backgroundCheckList.get(i).getResult());
                backgroundCheck.setCheckDate(backgroundCheckList.get(i).getCheckDate());
                break;
            }
        }
        int columns = backgroundCheckService.getColumnCount() - 4;
        double[] weights = {0.2, 0.15, 0.30, 0.35};
        double normalize = backgroundCheck.getJobTitleVerification() * weights[0] + backgroundCheck.getWageCompliance() * weights[1] +
                backgroundCheck.getBeneficiaryQualification() * weights[2] + backgroundCheck.getCriminalRecord() * weights[3];
        normalize = Math.round(normalize * 100) / 100.0;
        String riskLevel = "High";
        if (normalize >= 5.0) {
            riskLevel = "Moderate";
        }
        if (normalize >= 7.5) {
            riskLevel = "Low";
        }
        model.addAttribute("riskLevel", riskLevel);
        model.addAttribute("normalize", normalize);
        model.addAttribute("backgroundCheck", backgroundCheck);
    }

    @PostMapping("/approveForm")
    public String approveForm(@RequestParam("id") int id, Model model) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (id == visaApplicationList.get(i).getApplicationId()) {
                setBeneficiary(id, beneficiary, beneficiaryList, i);
                visaApplicationList.get(i).setStatus("3");
                break;
            }
        }
        setBeneficiary(id, model);
        setPetitioner(id, model);
        beneficiaryService.updateBeneficiary(id, beneficiary.getLastName(), beneficiary.getFirstName(), beneficiary.getMiddleName(),
                beneficiary.getDOB(), beneficiary.getGender(), beneficiary.getCountry(), beneficiary.getPassportNumber(), beneficiary.getEducation());
        return "approveID";
    }

    @PostMapping("/denyForm")
    public String denyForm(@RequestParam("id") int id, Model model) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (id == visaApplicationList.get(i).getApplicationId()) {
                setBeneficiary(id, beneficiary, beneficiaryList, i);
                visaApplicationList.get(i).setStatus("4");
                break;
            }
        }
        setBeneficiary(id, model);
        setPetitioner(id, model);
        beneficiaryService.updateBeneficiary(id, beneficiary.getLastName(), beneficiary.getFirstName(), beneficiary.getMiddleName(),
                beneficiary.getDOB(), beneficiary.getGender(), beneficiary.getCountry(), beneficiary.getPassportNumber(), beneficiary.getEducation());
        return "approveID";
    }

    @PostMapping("/reviewForm")
    public String reviewForm(@RequestParam("id") int id, Model model) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (id == visaApplicationList.get(i).getApplicationId()) {
                setBeneficiary(id, beneficiary, beneficiaryList, i);
                visaApplicationList.get(i).setStatus("2");
                break;
            }
        }
        setBeneficiary(id, model);
        setPetitioner(id, model);
        beneficiaryService.updateBeneficiary(id, beneficiary.getLastName(), beneficiary.getFirstName(), beneficiary.getMiddleName(),
                beneficiary.getDOB(), beneficiary.getGender(), beneficiary.getCountry(), beneficiary.getPassportNumber(), beneficiary.getEducation());
        return "approveID";
    }

    @GetMapping("/searchDate")
    public String searchDate(DateForm dateForm, Model model) {
        LocalDate startDate = dateForm.getStartDate();
        LocalDate endDate = dateForm.getEndDate();

        List<Integer> ids = beneficiaryService.findIdsInRange(startDate, endDate);
        int startYear = startDate.getYear();
        Month startMonth = startDate.getMonth();
        int startDay = startDate.getDayOfMonth();
        String start = startMonth + " " + startDay + ", " + startYear;

        int endYear = endDate.getYear();
        Month endMonth = endDate.getMonth();
        int endDay = endDate.getDayOfMonth();
        String end = endMonth + " " + endDay + ", " + endYear;

        model.addAttribute("ids", ids);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "displayIDs";
    }

    @GetMapping("displayForm")
    public String displayForm(@RequestParam("id") int id, Model model) {
        setBeneficiary(id, model);
        setPetitioner(id, model);
        return "approve";
    }
}

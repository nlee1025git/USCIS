package gov.uscis.nonimmigrant.worker.controller;

import gov.uscis.nonimmigrant.worker.domain.*;
import gov.uscis.nonimmigrant.worker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.sql.Date;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReviewController {
    private final BeneficiaryService beneficiaryService;
    private final PetitionerService petitionerService;
    private final BackgroundCheckService backgroundCheckService;
    private final CountryService countryService;
    private final VisaApplicationService visaApplicationService;
    private final VisaApplicationPredictorService visaApplicationPredictorService;

    @Autowired
    public ReviewController(BeneficiaryService beneficiaryService, PetitionerService petitionerService, BackgroundCheckService backgroundCheckService,
                            CountryService countryService, VisaApplicationService visaApplicationService, VisaApplicationPredictorService visaApplicationPredictorService) {
        this.beneficiaryService = beneficiaryService;
        this.petitionerService = petitionerService;
        this.backgroundCheckService = backgroundCheckService;
        this.countryService = countryService;
        this.visaApplicationService = visaApplicationService;
        this.visaApplicationPredictorService = visaApplicationPredictorService;
    }

    @GetMapping("reviewLogin")
    public String reviewLogin() {
        return "reviewLogin";
    }

    @PostMapping("/reviewSearch")
    public String searchID(Model model) {
        addCountries(model);
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        model.addAttribute("beneficiaryList", beneficiaryList);
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        model.addAttribute("visaApplicationList", visaApplicationList);

        List<VisaApplication> pendingList = new ArrayList<>();
        List<Beneficiary> pendingList2 = new ArrayList<>();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (visaApplicationList.get(i).getStatus().equals("1")) {
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
        return "reviewSearch";
    }

    @GetMapping("/search")
    public String searchID(@RequestParam("id") int id, Model model) {
        VisaApplication visaApplication = setApplication(id);
        model.addAttribute("visaApplication", visaApplication);
        setBeneficiary(visaApplication.getBeneficiaryId(), model);
        setPetitioner(visaApplication.getPetitionerId(), model);
        setBackgroundCheck(id, model, visaApplication.getBeneficiaryId());
        return "edit";
    }

    @GetMapping("/searchByCountry")
    public String searchByCountry(@RequestParam("country") String country, Model model) {
        List<Integer> ids = new ArrayList<>();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        for (int i = 0; i < beneficiaryList.size(); i++) {
            if (beneficiaryList.get(i).getCountry().equals(country)) {
                ids.add(beneficiaryList.get(i).getId());
            }
        }
        model.addAttribute("ids", ids);
        return "reviewCountries";
    }

    @GetMapping("/edit")
    public String editBeneficiary(@RequestParam("id") int id, Model model) {
        VisaApplication visaApplication = setApplication(id);
        model.addAttribute("visaApplication", visaApplication);
        setBeneficiary(visaApplication.getBeneficiaryId(), model);
        setPetitioner(visaApplication.getPetitionerId(), model);
        setBackgroundCheck(id, model, visaApplication.getBeneficiaryId());
        return "edit";
    }

    @GetMapping("/editForm")
    public String editForm(@RequestParam("id") int id, Model model) {
        VisaApplication visaApplication = setApplication(id);
        model.addAttribute("visaApplication", visaApplication);
        setBeneficiary(visaApplication.getBeneficiaryId(), model);
        setPetitioner(visaApplication.getPetitionerId(), model);
        return "editForm";
    }
    
    private void setBeneficiary(int id, Model model) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        for (int i = 0; i < beneficiaryList.size(); i++) {
            if (beneficiaryList.get(i).getId() == id) {
                beneficiary.setId(id);
                beneficiary.setLastName(beneficiaryList.get(i).getLastName());
                beneficiary.setFirstName(beneficiaryList.get(i).getFirstName());
                beneficiary.setMiddleName(beneficiaryList.get(i).getMiddleName());
                beneficiary.setDOB(beneficiaryList.get(i).getDOB());
                beneficiary.setGender(beneficiaryList.get(i).getGender());
                beneficiary.setCountry(beneficiaryList.get(i).getCountry());
                beneficiary.setPassportNumber(beneficiaryList.get(i).getPassportNumber());
                beneficiary.setEducation(beneficiaryList.get(i).getEducation());
                break;
            }
        }
        model.addAttribute("beneficiary", beneficiary);
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

    private void setBackgroundCheck(int id, Model model, int beneficiaryId) {
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

        Beneficiary beneficiary = setBeneficiary(beneficiaryId);
        int columns = backgroundCheckService.getColumnCount() - 4;
        double[] weights = {0.2, 0.15, 0.30, 0.35};
        double normalize = backgroundCheck.getJobTitleVerification() * weights[0] + backgroundCheck.getWageCompliance() * weights[1] +
                backgroundCheck.getBeneficiaryQualification() * weights[2] + backgroundCheck.getCriminalRecord() * weights[3];
        if (isFromCountry(beneficiary.getCountry())) {
            normalize -= 2;
        }
        normalize = Math.round(normalize * 100) / 100.0;

        model.addAttribute("normalize", normalize);
        model.addAttribute("backgroundCheck", backgroundCheck);

        float[] thresholds = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
        String startColor = "#0000ff";
        String endColor = "#ff0000";
        List<String> colorList = IntStream.range(0, 10)
                .mapToObj(i -> jetColormap(thresholds[i]))
                .map(ReviewController::colorToHex)
                .collect(Collectors.toList());
        Map<Integer, String> colorMap = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> colorList.get(i)));

        List<BackgroundCheck> backgroundChecks = backgroundCheckService.findBackgrounds();
        List<Beneficiary> beneficiaries = beneficiaryService.findBeneficiaries();
        model.addAttribute("backgroundChecks", backgroundChecks);
        model.addAttribute("beneficiaries", beneficiaries);
        List<Map<Object, Object>> combined = new ArrayList<>();
        List<Object> colors = new ArrayList<>();
        for (int i = 0; i < backgroundChecks.size(); i++) {
            colors.add(colorToHex(jetColormap(backgroundChecks.get(i).getJobTitleVerification() * 0.1f)));
        }
        for (int i = 0; i < backgroundChecks.size(); i++) {
            if (beneficiaries.get(i).getId() != beneficiaryId) {
                combined.add(Map.of("beneficiaries", beneficiaries.get(i), "backgroundChecks", backgroundChecks.get(i)));
            }
        }
        model.addAttribute("combined", combined);
        model.addAttribute("colors", colors);
//        model.addAttribute("backgroundChecks", backgroundChecks);
//        model.addAttribute("beneficiaries", beneficiaries);
        ReadController readController = new ReadController();
        List<List<String>> data = readController.getData();

//        System.out.println("a");
//        System.out.println(data);
//        System.out.println(data.toString());
//        System.out.println(colorToHex(jetColormap(backgroundCheck.getJobTitleVerification() * 0.1f)));

        model.addAttribute("colorMap", colorMap);
        model.addAttribute("colorList", colorList);
        model.addAttribute("job", colorToHex(jetColormap(backgroundCheck.getJobTitleVerification() * 0.1f)));
        model.addAttribute("wage", colorToHex(jetColormap(backgroundCheck.getWageCompliance() * 0.1f)));
        model.addAttribute("qualification", colorToHex(jetColormap(backgroundCheck.getBeneficiaryQualification() * 0.1f)));
        model.addAttribute("record", colorToHex(jetColormap(backgroundCheck.getCriminalRecord() * 0.1f)));
        model.addAttribute("total", colorToHex(jetColormap((float) normalize * 0.1f)));
        model.addAttribute("minimum", colorToHex(jetColormap(0.25f)));
    }

    public static Color jetColormap(float value) {
        value = Math.max(0, Math.min(value, 1));

        if (value <= 0.11) {
            return new Color(0, 0, 139);
        } else if (value <= 0.16) {
            return new Color(0, 0, 197);
        } else if (value <= 0.21) {
            return new Color(0, 0, 255);
        } else if (value <= 0.26) {
            return new Color(0, 97, 255);
        } else if (value <= 0.31) {
            return new Color(0, 191, 255);
        } else if (value <= 0.36) {
            return new Color(0, 223, 255);
        } else if (value <= 0.41) {
            return new Color(0, 255, 255);
        } else if (value <= 0.46) {
            return new Color(25, 230, 150);
        } else if (value <= 0.51) {
            return new Color(50, 205, 50);
        } else if (value <= 0.56) {
            return new Color(25, 235, 25);
        } else if (value <= 0.61) {
            return new Color(0, 255, 0);
        } else if (value <= 0.66) {
            return new Color(127, 255, 0);
        } else if (value <= 0.71) {
            return new Color(255, 255, 0);
        } else if (value <= 0.76) {
            return new Color(255, 200, 0);
        } else if (value <= 0.81) {
            return new Color(255, 165, 0);
        } else if (value <= 0.86) {
            return new Color(255, 100, 0);
        } else if (value <= 0.91) {
            return new Color(255, 69, 0);
        } else if (value <= 0.96) {
            return new Color(255, 35, 0);
        } else {
            return new Color(255, 0, 0); // Red
        }
    }

    private static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
    }

    private String interpolateColor(String startColor, String endColor, double ratio) {
        ratio = Math.max(0, Math.min(ratio, 1));
        int[] startRGB = hexToRGB(startColor);
        int[] endRGB = hexToRGB(endColor);

        int r = (int) (startRGB[0] + (endRGB[0] - startRGB[0]) * ratio);
        int g = (int) (startRGB[1] + (endRGB[1] - startRGB[1]) * ratio);
        int b = (int) (startRGB[2] + (endRGB[2] - startRGB[2]) * ratio);

        return rgbToHex(r, g, b);
    }

    private int[] hexToRGB(String hex) {
        hex = hex.replace("#", "");
        return new int[]{
                Integer.parseInt(hex.substring(0, 2), 16),
                Integer.parseInt(hex.substring(2, 4), 16),
                Integer.parseInt(hex.substring(4, 6), 16)
        };
    }

    private String rgbToHex(int r, int g, int b) {
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private Beneficiary setBeneficiary(int id) {
        Beneficiary beneficiary = new Beneficiary();
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        for (int i = 0; i < beneficiaryList.size(); i++) {
            if (beneficiaryList.get(i).getId() == id) {
                beneficiary.setId(id);
                beneficiary.setLastName(beneficiaryList.get(i).getLastName());
                beneficiary.setFirstName(beneficiaryList.get(i).getFirstName());
                beneficiary.setMiddleName(beneficiaryList.get(i).getMiddleName());
                beneficiary.setDOB(beneficiaryList.get(i).getDOB());
                beneficiary.setGender(beneficiaryList.get(i).getGender());
                beneficiary.setCountry(beneficiaryList.get(i).getCountry());
                beneficiary.setPassportNumber(beneficiaryList.get(i).getPassportNumber());
                beneficiary.setEducation(beneficiaryList.get(i).getEducation());
                break;
            }
        }
        return beneficiary;
    }

    private boolean isFromCountry(String country) {
        String[] dangerousCountries = {"Iran", "China", "Russia", "North Korea", "Pakistan", "Afghanistan", "Syria", "Iraq", "Venezuela", "Somalia"};
        for (int i = 0; i < dangerousCountries.length; i++) {
            if (dangerousCountries[i].equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, @RequestParam("lastName") String lastName,
                             @RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
                             @RequestParam("dob") Date dob, @RequestParam("gender") String gender,
                             @RequestParam("country") String country, @RequestParam("passportNumber") String passportNumber,
                             @RequestParam("education") String education, @RequestParam("last") String last,
                             @RequestParam("first") String first, @RequestParam("middle") String middle,
                             @RequestParam("companyName") String companyName, @RequestParam("address") String address,
                             @RequestParam("number") String number, @RequestParam("city") String city,
                             @RequestParam("state") String state, @RequestParam("zipcode") String zipcode,
                             @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email,
                             @RequestParam("fein") String fein, @RequestParam("applicationDate") Date applicationDate,
                             RedirectAttributes redirectAttributes) {
        VisaApplication visaApplication = setApplication(id);
        beneficiaryService.updateBeneficiary(visaApplication.getBeneficiaryId(), lastName, firstName, middleName, dob, gender, country, passportNumber, education);
        petitionerService.updatePetitioner(visaApplication.getPetitionerId(), last, first, middle, companyName, address, number, city, state, zipcode, phoneNumber, email, fein);
        redirectAttributes.addFlashAttribute("updateSuccess", true);
        return "redirect:/edit?id=" + id;
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("id") int id) {
        visaApplicationService.updateStatus(id, "2");
        return "redirect:/reviewSearch";
    }

    @PostMapping("/reject")
    public String reject(@RequestParam("reject_id") int id) {
        visaApplicationService.updateStatus(id, "3");
        return "redirect:/reviewSearch";
    }

    @GetMapping("/reviewSearch")
    public String reviewSearch(Model model) {
        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
        model.addAttribute("beneficiaryList", beneficiaryList);
        List<VisaApplication> visaApplicationList = visaApplicationService.findApplications();
        model.addAttribute("visaApplicationList", visaApplicationList);
        addCountries(model);

        List<VisaApplication> pendingList = new ArrayList<>();
        List<Beneficiary> pendingList2 = new ArrayList<>();
        for (int i = 0; i < visaApplicationList.size(); i++) {
            if (visaApplicationList.get(i).getStatus().equals("1")) {
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
        return "reviewSearch";
    }

    private void addCountries(Model model) {
        List<Country> countries = countryService.findCountries()
                .stream()
                .sorted(Comparator.comparing(Country::getCountry_name))
                .collect(Collectors.toList());
        model.addAttribute("countries", countries);
    }
}

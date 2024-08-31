package gov.uscis.nonimmigrant.worker.controller;

import gov.uscis.nonimmigrant.worker.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

//    @GetMapping("data-entry") // look up url
//    public String createForm(Model model) {
//        model.addAttribute("data", "data entry!");
//        return "dataEntry";
//    }
//
//    @PostMapping("data-entry") // deliever data form
//    public String create(PetitionForm form) {
//        Beneficiary beneficiary = new Beneficiary();
//        beneficiary.setName(form.getName());
//        beneficiary.setAddress(form.getAddress());
//        beneficiaryService.addData(beneficiary);
//        return "redirect:/";
//    }
//
//    @GetMapping("/data-entry-list")
//    public String list(Model model) {
//        List<Beneficiary> beneficiaryList = beneficiaryService.findBeneficiaries();
//        model.addAttribute("beneficiaryList", beneficiaryList);
//        return "beneficiary-list";
//    }
}

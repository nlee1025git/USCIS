package gov.uscis.nonimmigrant.worker.controller;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import gov.uscis.nonimmigrant.worker.repository.BeneficiaryRepository;
import gov.uscis.nonimmigrant.worker.service.BackgroundCheckService;
import gov.uscis.nonimmigrant.worker.service.BeneficiaryService;
import gov.uscis.nonimmigrant.worker.service.PetitionerService;
import gov.uscis.nonimmigrant.worker.service.VisaApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ApprovalControllerTest {
    @Autowired
    BeneficiaryService beneficiaryService;
    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @Autowired
    PetitionerService petitionerService;

    @Autowired
    VisaApplicationService visaApplicationService;

    @Autowired
    BackgroundCheckService backgroundCheckService;

    @Value("${}")

    @Test
    void testValidateLoginSuccess() {
        ApprovalController approvalController = new ApprovalController(beneficiaryService, petitionerService, visaApplicationService, backgroundCheckService);
        String username = "admin";
        String password = "a";

        boolean result = approvalController.validateLogin(username, password);
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    void testValidateLoginFailure() {
        ApprovalController approvalController = new ApprovalController(beneficiaryService, petitionerService, visaApplicationService, backgroundCheckService);
        String username = "admin";
        String password = "pass";

        boolean result = approvalController.validateLogin(username, password);
        assertFalse(result);
    }

    @Test
    void approveID() {
        int id = beneficiaryService.findBeneficiary(1).get().getId();
        assertEquals(1, id);
    }

    @Test
    void searchID() {
    }

    @Test
    void approveForm() {
    }

    @Test
    void denyForm() {
    }

    @Test
    void reviewForm() {
    }

    @Test
    void searchDate() {
    }

    @Test
    void displayForm() {
    }
}
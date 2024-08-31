package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.VisaApplication;
import gov.uscis.nonimmigrant.worker.repository.VisaApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisaApplicationService {
    private final VisaApplicationRepository visaApplicationRepository;

    @Autowired
    public VisaApplicationService(VisaApplicationRepository visaApplicationRepository) {
        this.visaApplicationRepository = visaApplicationRepository;
    }

    public void addApplication(VisaApplication visaApplication, int bid, int pid) {
        visaApplicationRepository.add(visaApplication, bid, pid);
    }

    public List<VisaApplication> findApplications() {
        return visaApplicationRepository.findAll();
    }

    public void updateStatus(int id, String status) {
        visaApplicationRepository.updateStatus(id, status);
    }
}

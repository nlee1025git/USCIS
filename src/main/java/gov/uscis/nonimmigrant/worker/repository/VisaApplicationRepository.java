package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.VisaApplication;

import java.util.List;

public interface VisaApplicationRepository {
    VisaApplication add(VisaApplication visaApplication, int bid, int pid);
    List<VisaApplication> findAll();

    void updateStatus(int id, String status);
}

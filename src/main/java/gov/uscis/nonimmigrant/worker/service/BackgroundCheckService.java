package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;
import gov.uscis.nonimmigrant.worker.repository.BackgroundCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundCheckService {
    private final BackgroundCheckRepository backgroundCheckRepository;

    @Autowired
    public BackgroundCheckService(BackgroundCheckRepository backgroundCheckRepository) {
        this.backgroundCheckRepository = backgroundCheckRepository;
    }

    public void addBackground(BackgroundCheck backgroundCheck) {
        backgroundCheckRepository.save(backgroundCheck);
    }

    public void addBackground(int id) {
        backgroundCheckRepository.add(id);
    }

    public List<BackgroundCheck> findBackgrounds() {
        return backgroundCheckRepository.findAll();
    }

    public int getColumnCount() {
        return backgroundCheckRepository.getCount();
    }

    public List<Integer> findJobs() {
        return backgroundCheckRepository.findAllJobs();
    }
}

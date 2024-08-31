package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;

import java.util.List;

public interface BackgroundCheckRepository {
    BackgroundCheck save(BackgroundCheck backgroundCheck);
    BackgroundCheck add(int id);

    List<BackgroundCheck> findAll();

    int getCount();
    List<Integer> findAllJobs();
}

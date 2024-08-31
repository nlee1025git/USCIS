package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> findAll();
}

package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.Country;
import gov.uscis.nonimmigrant.worker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findCountries() {
        return countryRepository.findAll();
    }
}

package gov.uscis.nonimmigrant.worker;

import gov.uscis.nonimmigrant.worker.repository.*;
import gov.uscis.nonimmigrant.worker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BeneficiaryService beneficiaryService() {
        return new BeneficiaryService(beneficiaryRepository());
    }

    @Bean
    public BeneficiaryRepository beneficiaryRepository() {
        return new JdbcBeneficiaryRepository(dataSource);
//        return new MemoryBeneficiaryRepository();
    }

    @Bean
    public PetitionerService petitionerService() {
        return new PetitionerService(petitionerRepository());
    }

    @Bean
    public PetitionerRepository petitionerRepository() {
        return new JdbcPetitionerRepository(dataSource);
//        return new MemoryPetitionerRepository();
    }

    @Bean
    public BackgroundCheckService backgroundCheckService() {
        return new BackgroundCheckService(backgroundCheckRepository());
    }

    @Bean
    public BackgroundCheckRepository backgroundCheckRepository() {
        return new JdbcBackgroundCheckRepository(dataSource);
    }

    @Bean
    public CountryService countryService() {
        return new CountryService(countryRepository());
    }

    @Bean
    public CountryRepository countryRepository() {
        return new JdbcCountryRepository(dataSource);
    }

    @Bean
    public VisaApplicationService visaApplicationService() {
        return new VisaApplicationService(visaApplicationRepository());
    }

    @Bean
    public VisaApplicationRepository visaApplicationRepository() {
        return new JdbcVisaApplicationRepository(dataSource);
    }
}

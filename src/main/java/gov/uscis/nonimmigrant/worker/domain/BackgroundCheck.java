package gov.uscis.nonimmigrant.worker.domain;

import java.sql.Date;

public class BackgroundCheck {
    private int backgroundId;
    private int applicationId;
    private int jobTitleVerification;
    private int wageCompliance;
    private int beneficiaryQualification;
    private int criminalRecord;
    private String result;
    private Date checkDate;

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobTitleVerification() {
        return jobTitleVerification;
    }

    public void setJobTitleVerification(int jobTitleVerification) {
        this.jobTitleVerification = jobTitleVerification;
    }

    public int getWageCompliance() {
        return wageCompliance;
    }

    public void setWageCompliance(int wageCompliance) {
        this.wageCompliance = wageCompliance;
    }

    public int getBeneficiaryQualification() {
        return beneficiaryQualification;
    }

    public void setBeneficiaryQualification(int beneficiaryQualification) {
        this.beneficiaryQualification = beneficiaryQualification;
    }

    public int getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(int criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}

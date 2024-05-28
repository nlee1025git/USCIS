package org.openjfx;

/**
 * This function is in charge of gathering the form's info and generate a
 * "petition" object.
 * 
 * @author Nam Kyu Lee.
 */
public class BusinessObject {
	/**
	 * Petitioner's last name
	 */
	private String petitionerLastName;

	/**
	 * Petitioner's first name
	 */
	private String petitionerFirstName;

	/**
	 * Petitioner's business name
	 */
	private String businessName;

	/**
	 * Petitioner's business address
	 */
	private String address;

	/**
	 * Petitioner's business phone number
	 */
	private String phoneNumber;

	/**
	 * Petitioner's business email
	 */
	private String email;

	/**
	 * Petitioner's business EIN
	 */
	private String EIN;

	/**
	 * Beneficiary's last name
	 */
	private String beneficiaryLastName;

	/**
	 * Beneficiary's first name
	 */
	private String beneficiaryFirstName;

	/**
	 * Beneficiary's date of birth
	 */
	private String beneficiaryDOB;

	/**
	 * Beneficiary's gender
	 */
	private String beneficiaryGender;

	/**
	 * Beneficiary's A-Number
	 */
	private String beneficiaryA_Number;

	/**
	 * Beneficiary's country of birth
	 */
	private String beneficiaryCountryOfBirth;

	/**
	 * Beneficiary's address
	 */
	private String beneficiaryAddress;

	/**
	 * Beneficiary's arrival date
	 */
	private String beneficiaryArrivalDate;

	/**
	 * Beneficiary's passport expiration date
	 */
	private String beneficiaryPassportExpirationDate;

	/**
	 * Work ID
	 */
	private String workID;

	/**
	 * Approve status
	 */
	private boolean approveStatus;

	/**
	 * Status of the email being sent
	 */
	private boolean emailSent;

	/**
	 * This constructor initializes the BO.
	 * @param petitionerLastName                petitioner's last name
	 * @param petitionerFirstName               petitioner's first name
	 * @param businessName                      petitioner's bussiness name
	 * @param address                           petitioner's bussiness address
	 * @param phoneNumber                       petitioner's bussiness phone number
	 * @param email                             petitioner's bussiness email
	 * @param EIN                               petitioner's bussiness EIN
	 * @param beneficiaryLastName               beneficiary's last name
	 * @param beneficiaryFirstName              beneficiary's first name
	 * @param beneficiaryDOB                    beneficiary's date of birth
	 * @param beneficiaryGender                 beneficiary's gender
	 * @param beneficiaryA_Number               beneficiary's A-Number
	 * @param beneficiaryCountryOfBirth         beneficiary's country of birth
	 * @param beneficiaryAddress                beneficiary's address
	 * @param beneficiaryArrivalDate            beneficiary's arrival date
	 * @param beneficiaryPassportExpirationDate beneficiary's passport expiration
	 *                                          date
	 * @param workID                            work ID
	 */
	public BusinessObject(String petitionerLastName, String petitionerFirstName, String businessName, String address,
			String phoneNumber, String email, String EIN, String beneficiaryLastName, String beneficiaryFirstName,
			String beneficiaryDOB, String beneficiaryGender, String beneficiaryA_Number,
			String beneficiaryCountryOfBirth, String beneficiaryAddress, String beneficiaryArrivalDate,
			String beneficiaryPassportExpirationDate, String workID) {
		this.petitionerLastName = petitionerLastName;
		this.petitionerFirstName = petitionerFirstName;
		this.businessName = businessName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.EIN = EIN;

		this.beneficiaryLastName = beneficiaryLastName;
		this.beneficiaryFirstName = beneficiaryFirstName;
		this.beneficiaryDOB = beneficiaryDOB;
		this.beneficiaryGender = beneficiaryGender;
		this.beneficiaryA_Number = beneficiaryA_Number;
		this.beneficiaryCountryOfBirth = beneficiaryCountryOfBirth;
		this.beneficiaryAddress = beneficiaryAddress;
		this.beneficiaryArrivalDate = beneficiaryArrivalDate;
		this.beneficiaryPassportExpirationDate = beneficiaryPassportExpirationDate;

		this.workID = workID;
	}
	
	/**
	 * This constructor serves to set all the data using setters instead of parameters.
	 */
	public BusinessObject() {
		
	}

	/**
	 * Returns the last name of the petitioner
	 * 
	 * @return petitioner's last name
	 */
	public String getPetitionerLastName() {
		return this.petitionerLastName;
	}

	/**
	 * Returns the first name of the petitioner
	 * 
	 * @return petitioner's first name
	 */
	public String getPetitionerFirstName() {
		return this.petitionerFirstName;
	}

	/**
	 * Returns the business name of the petitioner
	 * 
	 * @return petitioner's businessName name
	 */
	public String getBusinessName() {
		return this.businessName;
	}

	/**
	 * Returns the businessName address of the petitioner
	 * 
	 * @return petitioner's businessName address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Returns the businessName's phone number of the petitioner
	 * 
	 * @return petitioner's businessName phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Returns the businessName's email of the petitioner
	 * 
	 * @return petitioner's businessName email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Returns the businessName's EIN of the petitioner
	 * 
	 * @return petitioner's businessName EIN
	 */
	public String getEIN() {
		return this.EIN;
	}

	/**
	 * Returns the last name of the beneficiary
	 * 
	 * @return beneficiary's last name
	 */
	public String getBeneficiaryLastName() {
		return this.beneficiaryLastName;
	}

	/**
	 * Returns the first name of the beneficiary
	 * 
	 * @return beneficiary's first name
	 */
	public String getBeneficiaryFirstName() {
		return this.beneficiaryFirstName;
	}

	/**
	 * Returns the date of birth of the beneficiary
	 * 
	 * @return beneficiary's date of birth
	 */
	public String getBeneficiaryDOB() {
		return this.beneficiaryDOB;
	}

	/**
	 * Returns the gender of the beneficiary
	 * 
	 * @return beneficiary's gender
	 */
	public String getBeneficiaryGender() {
		return this.beneficiaryGender;
	}

	/**
	 * Returns the last name of the beneficiary
	 * 
	 * @return beneficiary's A-Number
	 */
	public String getBeneficiaryA_Number() {
		return this.beneficiaryA_Number;
	}

	/**
	 * Returns the country of birth of the beneficiary
	 * 
	 * @return beneficiary's country of birth
	 */
	public String getBeneficiaryCountryOfBirth() {
		return this.beneficiaryCountryOfBirth;
	}

	/**
	 * Returns the address of the beneficiary
	 * 
	 * @return beneficiary's address
	 */
	public String getBeneficiaryAddress() {
		return this.beneficiaryAddress;
	}

	/**
	 * Returns the arrival date of the beneficiary
	 * 
	 * @return beneficiary's arrival date
	 */
	public String getBeneficiaryArrivalDate() {
		return this.beneficiaryArrivalDate;
	}

	/**
	 * Returns the passport expiration date of the beneficiary
	 * 
	 * @return beneficiary's passport expiration date
	 */
	public String getBeneficiaryPassportExpirationDate() {
		return this.beneficiaryPassportExpirationDate;
	}

	/**
	 * Returns the work ID
	 * 
	 * @return work ID
	 */
	public String getWorkID() {
		return this.workID;
	}


	/**
	 * Returns the approve status of the beneficiary
	 * 
	 * @return approve status
	 */
	public boolean getApproveStatus() {
		return this.approveStatus;
	}

	/**
	 * Returns whether email has been sent to the beneficiary Returns whether the
	 * beneficiary
	 * 
	 * @return email status
	 */
	public boolean getEmailSent() {
		return this.emailSent;
	}

	/**
	 * Sets the last name of the petitioner
	 * 
	 * @param pLastName petitioner's new last name
	 */
	public void setPetitionerLastName(String pLastName) {
		this.petitionerLastName = pLastName;
	}

	/**
	 * Sets the first name of the petitioner
	 * 
	 * @param pFirstName petitioner's new first name
	 */
	public void setPetitionerFirstName(String pFirstName) {
		this.petitionerFirstName = pFirstName;
	}

	/**
	 * Sets the businessName name of the petitioner
	 * 
	 * @param businessName petitioner's new businessName name
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * Sets the petitioner's business address
	 * 
	 * @param address business's new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the petitioner's business phone number
	 * 
	 * @param phoneNumber business's new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Sets the petitioner's business email
	 * 
	 * @param email business's new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the petitioner's business EIN
	 * 
	 * @param EIN business's new EIN
	 */
	public void setEIN(String EIN) {
		this.EIN = EIN;
	}

	/**
	 * Sets the beneficiary's last name
	 * 
	 * @param bLastName beneficiary's new last name
	 */
	public void setBeneficiaryLastName(String bLastName) {
		this.beneficiaryLastName = bLastName;
	}

	/**
	 * Sets the beneficiary's first name
	 * 
	 * @param bFirstName beneficiary's new first name
	 */
	public void setBeneficiaryFirstName(String bFirstName) {
		this.beneficiaryFirstName = bFirstName;
	}

	/**
	 * Sets the beneficiary's date of birth
	 * 
	 * @param bDOB beneficiary's new date of birth
	 */
	public void setBeneficiaryDOB(String bDOB) {
		this.beneficiaryDOB = bDOB;
	}

	/**
	 * Sets the beneficiary's gender
	 * 
	 * @param bGender beneficiary's new gender
	 */
	public void setBeneficiaryGender(String bGender) {
		this.beneficiaryGender = bGender;
	}

	/**
	 * Sets the beneficiary's A-Number
	 * 
	 * @param bA_Number beneficiary's new A-Number
	 */
	public void setBeneficiaryA_Number(String bA_Number) {
		this.beneficiaryA_Number = bA_Number;
	}

	/**
	 * Sets the beneficiary's country of birth
	 * 
	 * @param bCountryOfBirth beneficiary's new country of birth
	 */
	public void setBeneficiaryCountryOfBirth(String bCountryOfBirth) {
		this.beneficiaryCountryOfBirth = bCountryOfBirth;
	}

	/**
	 * Sets the beneficiary's address
	 * 
	 * @param bAddress beneficiary's new address
	 */
	public void setBeneficiaryAddress(String bAddress) {
		this.beneficiaryAddress = bAddress;
	}

	/**
	 * Sets the beneficiary's arrival date
	 * 
	 * @param bArrivalDate beneficiary's new arrival date
	 */
	public void setBeneficiaryArrivalDate(String bArrivalDate) {
		this.beneficiaryArrivalDate = bArrivalDate;
	}

	/**
	 * Sets the beneficiary's passport expiartion date
	 * 
	 * @param bPasswordExpiratinoDate beneficiary's new expiration date
	 */
	public void setBeneficiaryPassportExpirationDate(String bPasswordExpiratinoDate) {
		this.beneficiaryPassportExpirationDate = bPasswordExpiratinoDate;
	}

	/**
	 * Sets the work ID
	 * 
	 * @param workID new work ID
	 */
	public void setWorkID(String workID) {
		this.workID = workID;
	}

	/**
	 * Sets the approve status
	 * 
	 * @param status new approve status
	 */
	public void setApproveStatus(boolean status) {
		this.approveStatus = status;
	}

	/**
	 * Sets the status of the email being sent
	 * 
	 * @param status update the status of email being sent
	 */
	public void setEmailSent(boolean email) {
		this.emailSent = email;
	}
}

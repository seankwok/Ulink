package ulink.constructor;

public class Client {
	private String passportNumber;
	private String clientName;
	private String gender;
	private String dateOfBirth;
	private String mainDianosis;
	private String nationality;
	private String countryOfResidence;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String billingCountry;
	private String billingCode;
	private String isMedicial;
	private String isClaim;
	private String claimInformation;
	//private String consultationDateTime;
	//private String referringDoctorEmail;
	//private String caseNumber;
	//private String visaType;
	//private String personInCharge;
	//private String followUpPerson;
	private String referralName;
	
	public Client(String passportNumber, String clientName, String gender, String dateOfBirth, String mainDianosis,
			String nationality, String countryOfResidence, String billingStreet, String billingCity,
			String billingState, String billingCountry, String billingCode, String isMedicial, String isClaim,
			String claimInformation, String referralName) {
		super();
		this.passportNumber = passportNumber;
		this.clientName = clientName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mainDianosis = mainDianosis;
		this.nationality = nationality;
		this.countryOfResidence = countryOfResidence;
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingCountry = billingCountry;
		this.billingCode = billingCode;
		this.isMedicial = isMedicial;
		this.isClaim = isClaim;
		this.claimInformation = claimInformation;
		this.referralName = referralName;
	}
	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}
	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public String getGender() {
		return gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getMainDianosis() {
		return mainDianosis;
	}
	public String getNationality() {
		return nationality;
	}
	public String getCountryOfResidence() {
		return countryOfResidence;
	}
	public String getBillingStreet() {
		return billingStreet;
	}
	public String getBillingCity() {
		return billingCity;
	}
	public String getBillingState() {
		return billingState;
	}
	public String getBillingCode() {
		return billingCode;
	}
	public String getIsMedicial() {
		return isMedicial;
	}
	public String getIsClaim() {
		return isClaim;
	}
	public String getClaimInformation() {
		return claimInformation;
	}
	public String getReferralName() {
		return referralName;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setMainDianosis(String mainDianosis) {
		this.mainDianosis = mainDianosis;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}
	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}
	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}
	public void setIsMedicial(String isMedicial) {
		this.isMedicial = isMedicial;
	}
	public void setIsClaim(String isClaim) {
		this.isClaim = isClaim;
	}
	public void setClaimInformation(String claimInformation) {
		this.claimInformation = claimInformation;
	}
	
	
}

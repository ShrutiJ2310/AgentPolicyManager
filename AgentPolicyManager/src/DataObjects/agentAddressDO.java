package DataObjects;

public class agentAddressDO {
	String agentAddressSeq; //mandatory
	String addressType;
	String countryCd;
	String stateCd;
	String addressDetails;
	String pinCode;
	String primaryAddress;
	
	@Override
	public String toString() {
		return "agentAddressDO [agentAddressSeq=" + agentAddressSeq
				+ ", addressType=" + addressType + ", countryCd=" + countryCd
				+ ", stateCd=" + stateCd + ", addressDetails=" + addressDetails
				+ ", pinCode=" + pinCode + ", primaryAddress=" + primaryAddress
				+ "]";
	}
	public String getAgentAddressSeq() {
		return agentAddressSeq;
	}
	public void setAgentAddressSeq(String agentAddressSeq) {
		this.agentAddressSeq = agentAddressSeq;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

}

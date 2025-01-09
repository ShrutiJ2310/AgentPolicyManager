package DataObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class agentDO {
	String agentSeq; //using singleton
	String designationCd;
	String reportsTo;
	String genderCd;
	String title;
	String agentFirstName;
	String agentLastName;
	Date licenceIssueDate;
	int validityPeriod;
	String channelCd;
	public List <agentContactDO> list_agentContactDO = new ArrayList<agentContactDO>();
	List <agentAddressDO> list_agentAddressDO = new ArrayList<agentAddressDO>();
	
	
	@Override
	public String toString() {
		return "agentDO\nagentSeq=" + agentSeq + "\ndesignationCd="
				+ designationCd + "\nreportsTo=" + reportsTo + "\ngenderCd="
				+ genderCd + "\ntitle=" + title + "\nagentFirstName="
				+ agentFirstName + ", agentLastName=" + agentLastName
				+ "\nchannelCd=" + channelCd + "\nlist_agentContactDO="
				+ list_agentContactDO + "\nlist_agentAddressDO="
				+ list_agentAddressDO+"\n";
	}

	public agentDO(String agentSeq, String designationCd, String reportsTo,
			String genderCd, String title, String agentFirstName,
			String agentLastName) {
		super();
		this.agentSeq = agentSeq;
		this.designationCd = designationCd;
		this.reportsTo = reportsTo;
		this.genderCd = genderCd;
		this.title = title;
		this.agentFirstName = agentFirstName;
		this.agentLastName = agentLastName;
	}
	
	public agentDO() {
		super();
	}

	public String getAgentSeq() {
		return agentSeq;
	}

	public void setAgentSeq(String agentSeq) {
		this.agentSeq = agentSeq;
	}

	public String getDesignationCd() {
		return designationCd;
	}

	public void setDesignationCd(String designationCd) {
		this.designationCd = designationCd;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getGenderCd() {
		return genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAgentFirstName() {
		return agentFirstName;
	}

	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}

	public String getAgentLastName() {
		return agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public Date getLicenceIssueDate() {
		return licenceIssueDate;
	}

	public void setLicenceIssueDate(Date licenceIssueDate) {
		this.licenceIssueDate = licenceIssueDate;
	}

	public int getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(int validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
	}

	public List<agentContactDO> getList_agentContactDO() {
		return list_agentContactDO;
	}

	public void setList_agentContactDO(List<agentContactDO> list_agentContactDO) {
		this.list_agentContactDO = list_agentContactDO;
	}

	public List<agentAddressDO> getList_agentAddressDO() {
		return list_agentAddressDO;
	}

	public void setList_agentAddressDO(List<agentAddressDO> list_agentAddressDO) {
		this.list_agentAddressDO = list_agentAddressDO;
	}

}

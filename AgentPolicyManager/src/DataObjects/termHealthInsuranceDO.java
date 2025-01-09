package DataObjects;

import java.util.List;

import operations.premiumCalc;

public class termHealthInsuranceDO extends basepolicyDO {
	String policySeq;
	Double premiumAmt;
	Double sumAssured;
	Double healthCover;
	List <RiderDO> list_riderDO;
	
	premiumCalc premiumObject = new premiumCalc();
	
	public termHealthInsuranceDO(String agentSeq, String partySeq,
			String partyName, String policySeq2, double sumAssuredAmt,
			double healthCover2, List<RiderDO> listOfRiders, int numOfRiders) {
		super(agentSeq, partySeq, partyName);
		this.sumAssured = sumAssuredAmt;
		this.policySeq = policySeq2;
		this.healthCover = healthCover2;
		this.list_riderDO = listOfRiders;
		this.premiumAmt = premiumObject.premiumCalTermHealthIns(sumAssuredAmt, sumAssuredAmt*0.02, numOfRiders);
	}

	public String getPolicySeq() {
		return policySeq;
	}

	public void setPolicySeq(String policySeq) {
		this.policySeq = policySeq;
	}

	public Double getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(Double premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public Double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public Double getHealthCover() {
		return healthCover;
	}

	public void setHealthCover(Double healthCover) {
		this.healthCover = healthCover;
	}

	public List<RiderDO> getList_riderDO() {
		return list_riderDO;
	}

	public void setList_riderDO(List<RiderDO> list_riderDO) {
		this.list_riderDO = list_riderDO;
	}
	
}

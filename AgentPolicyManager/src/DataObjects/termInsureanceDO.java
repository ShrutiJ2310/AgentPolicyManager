package DataObjects;

import java.util.List;

import operations.premiumCalc;

public class termInsureanceDO extends basepolicyDO {
	String policySeq;
	Double premiumAmt;
	Double sumAssured;
	List <RiderDO> list_riderDO;
	
	premiumCalc premCalObject = new premiumCalc();

	public termInsureanceDO(String policySeq2, double coverPerRider,
			int numOfRiders, double SumAssured, List<RiderDO> listOfRiders,
			String agentSeq, String partySeq, String partyName) 
	{
		super(agentSeq, partySeq, partyName);
		this.policySeq = policySeq2;
		this.sumAssured = SumAssured;
		this.list_riderDO = listOfRiders;
		this.premiumAmt = premCalObject.premiumCalTermIns(SumAssured,
				coverPerRider, numOfRiders);
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

	public List<RiderDO> getList_riderDO() {
		return list_riderDO;
	}

	public void setList_riderDO(List<RiderDO> list_riderDO) {
		this.list_riderDO = list_riderDO;
	}

}
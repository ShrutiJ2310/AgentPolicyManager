package DataObjects;

import java.util.Date;

public class CommTransactionDO {
	String transactionId; //unique using singleton class
	String policySeq;
	double commissionAmt;
	Date dateTime;
	
	@Override
	public String toString() {
		return "CommTransactionDO [transactionId=" + transactionId
				+ ", policySeq=" + policySeq + ", commissionAmt="
				+ commissionAmt + ", dateTime=" + dateTime + "]";
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPolicySeq() {
		return policySeq;
	}
	public void setPolicySeq(String policySeq) {
		this.policySeq = policySeq;
	}
	public double getCommissionAmt() {
		return commissionAmt;
	}
	public void setCommissionAmt(double commissionAmt) {
		this.commissionAmt = commissionAmt;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	

}

package DataObjects;

public class RiderDO {
	String riderSeq; //unique using singleton
	String riderName;
	double coverAmount;
	String riderDescription;
	
	public RiderDO(String riderSeq, String riderName, double coverAmount,
			String riderDescription) {
		super();
		this.riderSeq = riderSeq;
		this.riderName = riderName;
		this.coverAmount = coverAmount;
		this.riderDescription = riderDescription;
	}
	
	public RiderDO() {
		super();
	}

	@Override
	public String toString() {
		return "RiderDO [riderSeq=" + riderSeq + ", riderName=" + riderName
				+ ", coverAmount=" + coverAmount + ", riderDescription="
				+ riderDescription + "]";
	}
	public String getRiderSeq() {
		return riderSeq;
	}
	public void setRiderSeq(String riderSeq) {
		this.riderSeq = riderSeq;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public double getCoverAmount() {
		return coverAmount;
	}
	public void setCoverAmount(double coverAmount) {
		this.coverAmount = coverAmount;
	}
	public String getRiderDescription() {
		return riderDescription;
	}
	public void setRiderDescription(String riderDescription) {
		this.riderDescription = riderDescription;
	}

	
}

package DataObjects;

public class basepolicyDO implements termAndConditionsNTF {
	String agentSeq; //unique using singleton class
	String partySeq; //unique using singleton class
	String partyName;
	
	public basepolicyDO(String agentSeq, String partySeq, String partyName) {
		super();
		this.agentSeq = agentSeq;
		this.partySeq = partySeq;
		this.partyName = partyName;
		System.out.println("\nAgentSeq: "+agentSeq+"\nPartySeq: "+partySeq+"\nPartyName: "+partyName);
	}

	public void disclaimerMessage(){
		System.out.println("");
	}

	public String getAgentSeq() {
		return agentSeq;
	}

	public void setAgentSeq(String agentSeq) {
		this.agentSeq = agentSeq;
	}

	public String getPartySeq() {
		return partySeq;
	}

	public void setPartySeq(String partySeq) {
		this.partySeq = partySeq;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
}

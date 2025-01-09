package operations;

public class sequenceGenerator {
	static int agentSeq;
	static int agentAddressSeq;
	static int riderSeq;
	static int partySeq;
	static int policySeq;
	static int transactionID;
	static int contactSeq;
	static sequenceGenerator SeqGen;
	
	public sequenceGenerator() {
		super();
		agentSeq = 1;
		agentAddressSeq = 1;
		riderSeq = 1;
		partySeq = 1;
		policySeq = 1;
		transactionID = 1;
		contactSeq = 1;
	}
	
	public static sequenceGenerator getInstance(){
		if(SeqGen==null){
			SeqGen = new sequenceGenerator();
			return SeqGen;
		}else{
			return SeqGen;
		}
		
	}
	
	
	

}

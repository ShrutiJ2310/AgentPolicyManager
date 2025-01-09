package operations;

import java.util.LinkedHashMap;
import java.util.List;

import DataObjects.CommTransactionDO;

public class viewAgentCommissionData
{
	public void viewCommissionData(LinkedHashMap<String, List<CommTransactionDO>> LHMCommData, String agentSeq)
	{
		List<CommTransactionDO> listOfAgentCommTrans = LHMCommData.get(agentSeq);
		System.out.println("AgenSeq: "+agentSeq);
		
		for(CommTransactionDO perCommObj: listOfAgentCommTrans)
		{
			System.out.println("\nPolicy Sequence: "+perCommObj.getPolicySeq()+
					"\nTransaction ID: "+perCommObj.getTransactionId()+
					"\nDate and Time: "+perCommObj.getDateTime()+
					"\nCommission amount: "+perCommObj.getCommissionAmt()+"\n");
		}
	}

}

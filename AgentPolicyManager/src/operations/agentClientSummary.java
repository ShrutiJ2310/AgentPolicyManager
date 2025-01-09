package operations;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import DataObjects.basepolicyDO;

public class agentClientSummary {
	
	public void agentAndClientSummary(LinkedHashMap<String, LinkedHashMap<String, basepolicyDO>> agentSales)
	{
		LinkedHashMap<String, basepolicyDO> policySales = new LinkedHashMap<String, basepolicyDO>(); 
		
		for(Entry<String, LinkedHashMap<String, basepolicyDO>> perAgent: agentSales.entrySet())
		{
			System.out.println("Agent Seq: "+perAgent.getKey()+"\nHere is the list of policies he has sold:");
			policySales = perAgent.getValue();
			for(Entry<String, basepolicyDO> perPol: policySales.entrySet())
			{
				System.out.println(perPol.getKey()+" ");
			}
		}
	}

}

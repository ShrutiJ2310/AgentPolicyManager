package operations;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import DataObjects.CommTransactionDO;
import DataObjects.agentDO;
import DataObjects.basepolicyDO;
import DataObjects.termHealthInsuranceDO;
import DataObjects.termInsureanceDO;

public class CommissionClass {

	public void CommRollout(
			LinkedHashMap<String, LinkedHashMap<String, basepolicyDO>> agentSales,
			LinkedHashMap<String, basepolicyDO> policySales,
			LinkedHashMap<String, agentDO> lhmListOfAgents,
			LinkedHashMap<String, List<CommTransactionDO>> LHMCommData, String PartySeq) 
	{
		double firstLevel = 0.05;
		double secondLevel = 0.02;
		double thirdLevel = 0.01;
		
		Scanner t = new Scanner(System.in);
		List<String> PolicySequencesOwned = new ArrayList<String>();
		
		//Checking for policies owned by party seq
		for(Entry<String, basepolicyDO> perPolicy: policySales.entrySet())
		{
			if(perPolicy.getValue().getPartySeq().equalsIgnoreCase(PartySeq))
			{
				PolicySequencesOwned.add(perPolicy.getKey());
			}
		}
		
		System.out.println("Here is the list of policies owned by the party Sequence");
		for(String perPol: PolicySequencesOwned)
		{
			System.out.println(perPol);
		}
		
		System.out.println("Which policy do you wish to pay for?\nEnter the policy Sequence: ");
		String polSeqToPay = t.nextLine();
		
		basepolicyDO baseObject = policySales.get(polSeqToPay);
		
		//If instance of term insurance
		 if(baseObject instanceof termInsureanceDO){
			 String agentSeq = baseObject.getAgentSeq();
			 agentDO agentComm = lhmListOfAgents.get(agentSeq);
			 
			 if(agentComm.getDesignationCd().equalsIgnoreCase("AGENT"))
			 {
				 CommTransactionDO CommDOlev1 = new CommTransactionDO();
				 CommTransactionDO CommDOlev2 = new CommTransactionDO();
				 CommTransactionDO CommDOlev3 = new CommTransactionDO();
				
				 Date date = new Date();
				 
				 double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				 
				 CommDOlev1.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
			   	 CommDOlev1.setPolicySeq(polSeqToPay);
				 CommDOlev1.setDateTime(date);
				 CommDOlev1.setCommissionAmt(premiumAmt*firstLevel);
				 
				 CommDOlev2.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev2.setPolicySeq(polSeqToPay);
				 CommDOlev2.setDateTime(date);
				 CommDOlev2.setCommissionAmt(premiumAmt*secondLevel);
				 
				 CommDOlev3.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev3.setPolicySeq(polSeqToPay);
				 CommDOlev3.setDateTime(date);
				 CommDOlev3.setCommissionAmt(premiumAmt*thirdLevel);
				 
				 agentDO agent = lhmListOfAgents.get(agentSeq);
				 String agentManagerSeqLevel2 = agent.getReportsTo();
				 agentDO agentLevel2 = lhmListOfAgents.get(agentManagerSeqLevel2);
				 String agentManagerSeqLevel3 = agentLevel2.getReportsTo();
				 
				 LHMCommData.get(agentSeq).add(CommDOlev1);
				 LHMCommData.get(agentManagerSeqLevel2).add(CommDOlev2);
				 LHMCommData.get(agentManagerSeqLevel3).add(CommDOlev3);
				
			 }
			 else if(agentComm.getDesignationCd().equalsIgnoreCase("AGENT MANAGER"))
			 {
				
				 CommTransactionDO CommDOlev1 = new CommTransactionDO();
				 CommTransactionDO CommDOlev2 = new CommTransactionDO();
				 
				 Date date = new Date();
				 
				 double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				 
				 CommDOlev1.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
			   	 CommDOlev1.setPolicySeq(polSeqToPay);
				 CommDOlev1.setDateTime(date);
				 CommDOlev1.setCommissionAmt(premiumAmt*firstLevel);
				 
				 CommDOlev2.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev2.setPolicySeq(polSeqToPay);
				 CommDOlev2.setDateTime(date);
				 CommDOlev2.setCommissionAmt(premiumAmt*secondLevel);
				 
				 String agentManagerReportsToSeq = agentComm.getReportsTo();
				 
				 LHMCommData.get(agentSeq).add(CommDOlev1);
				 LHMCommData.get(agentManagerReportsToSeq).add(CommDOlev2);
				 
			 }
			 else
			 {
				CommTransactionDO CommDO = new CommTransactionDO();
				Date date = new Date();
				CommDO.setPolicySeq(polSeqToPay);
				CommDO.setDateTime(date);

				CommDO.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));

				Double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				CommDO.setCommissionAmt(premiumAmt * firstLevel);
				
				LHMCommData.get(agentSeq).add(CommDO);
				
			 }
		 }
		 
		 //If instance of term health insurance
		 if(baseObject instanceof termHealthInsuranceDO)
		 {
			 String agentSeq = baseObject.getAgentSeq();
			 agentDO agentComm = lhmListOfAgents.get(agentSeq);
			 
			 if(agentComm.getDesignationCd().equalsIgnoreCase("Agent"))
			 {
				 CommTransactionDO CommDOlev1 = new CommTransactionDO();
				 CommTransactionDO CommDOlev2 = new CommTransactionDO();
				 CommTransactionDO CommDOlev3 = new CommTransactionDO();
				
				 Date date = new Date();
				 
				 double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				 
				 CommDOlev1.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
			   	 CommDOlev1.setPolicySeq(polSeqToPay);
				 CommDOlev1.setDateTime(date);
				 CommDOlev1.setCommissionAmt(premiumAmt*firstLevel);
				 
				 CommDOlev2.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev2.setPolicySeq(polSeqToPay);
				 CommDOlev2.setDateTime(date);
				 CommDOlev2.setCommissionAmt(premiumAmt*secondLevel);
				 
				 CommDOlev3.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev3.setPolicySeq(polSeqToPay);
				 CommDOlev3.setDateTime(date);
				 CommDOlev3.setCommissionAmt(premiumAmt*thirdLevel);
				 
				 agentDO agent = lhmListOfAgents.get(agentSeq);
				 String agentManagerSeqLevel2 = agent.getReportsTo();
				 agentDO agentLevel2 = lhmListOfAgents.get(agentManagerSeqLevel2);
				 String agentManagerSeqLevel3 = agentLevel2.getReportsTo();
				 
				 LHMCommData.get(agentSeq).add(CommDOlev1);
				 LHMCommData.get(agentManagerSeqLevel2).add(CommDOlev2);
				 LHMCommData.get(agentManagerSeqLevel3).add(CommDOlev3);
				
				 
			 }
			 else if(agentComm.getDesignationCd().equalsIgnoreCase("Agent Manager"))
			 {
				
				 CommTransactionDO CommDOlev1 = new CommTransactionDO();
				 CommTransactionDO CommDOlev2 = new CommTransactionDO();
				 
				 Date date = new Date();
				 
				 double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				 
				 CommDOlev1.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
			   	 CommDOlev1.setPolicySeq(polSeqToPay);
				 CommDOlev1.setDateTime(date);
				 CommDOlev1.setCommissionAmt(premiumAmt*firstLevel);
				 
				 CommDOlev2.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));
				 CommDOlev2.setPolicySeq(polSeqToPay);
				 CommDOlev2.setDateTime(date);
				 CommDOlev2.setCommissionAmt(premiumAmt*secondLevel);
				 
				 String agentManagerReportsToSeq = agentComm.getReportsTo();
				 
				 LHMCommData.get(agentSeq).add(CommDOlev1);
				 LHMCommData.get(agentManagerReportsToSeq).add(CommDOlev2);
				 
			 }
			 else
			 {
				CommTransactionDO CommDO = new CommTransactionDO();
				Date date = new Date();
				CommDO.setPolicySeq(polSeqToPay);
				CommDO.setDateTime(date);

				CommDO.setTransactionId(String.valueOf(sequenceGenerator.transactionID++));

				Double premiumAmt = ((termInsureanceDO) baseObject).getPremiumAmt();
				CommDO.setCommissionAmt(premiumAmt * firstLevel);
				
				LHMCommData.get(agentSeq).add(CommDO);
				
			 }
			
			
			
			
		 }
		
	}
}

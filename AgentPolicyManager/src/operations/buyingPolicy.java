package operations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import DataObjects.CommTransactionDO;
import DataObjects.RiderDO;
import DataObjects.agentDO;
import DataObjects.basepolicyDO;
import DataObjects.termHealthInsuranceDO;
import DataObjects.termInsureanceDO;

public class buyingPolicy 
{
	Scanner t = new Scanner(System.in);
	
	public void ListingPolicyAndRiders(LinkedHashMap<String, LinkedHashMap<String, basepolicyDO>> agentSales, 
			LinkedHashMap<String, basepolicyDO> policySales, LinkedHashMap<String, agentDO> lhmListOfAgents
			, LinkedHashMap<String, List<CommTransactionDO>> lHMCommData)
	{
		ArrayList <String> TermInsRidersToShow = new ArrayList <String>();
		ArrayList <String> TermHealthInsRidersToShow = new ArrayList <String>();
		
		LinkedHashMap<String,ArrayList <String>> PoliciesAndRidersAvail = new LinkedHashMap<String, ArrayList<String>>();
		
		TermInsRidersToShow.add("Critical Illness benefit Rider");
		TermInsRidersToShow.add("Accidental Death benefit Rider");
		TermInsRidersToShow.add("Terminal illness benefit Rider");
		TermInsRidersToShow.add("Permanent Disability benefir Rider");
		
		TermHealthInsRidersToShow.add("Critical illness Rider");
		TermHealthInsRidersToShow.add("OPD Rider");
		TermHealthInsRidersToShow.add("Maternity Rider");
		TermHealthInsRidersToShow.add("Room rent waiver Rider");
		TermHealthInsRidersToShow.add("Personal accident Rider");
		TermHealthInsRidersToShow.add("Global cover Rider");
		
		PoliciesAndRidersAvail.put("Term Insurance", TermInsRidersToShow );
		PoliciesAndRidersAvail.put("Term Health Insurance", TermHealthInsRidersToShow);
		
		while (true) {
			System.out.println("Here is the list of policies that we have");
			int i1 = 1;
			// Displaying policies available
			for (Entry<String, ArrayList<String>> perObject : PoliciesAndRidersAvail.entrySet()) 
			{
				System.out.println(i1 + ") " + perObject.getKey());
				i1++;
			}

			System.out.println("Select one and enter you choice in accordance with the number");
			int polChoice = t.nextInt();

			i1 = 1;
			for (Entry<String, ArrayList<String>> perObject : PoliciesAndRidersAvail.entrySet()) 
			{
				if(polChoice==i1)
				{
					if(perObject.getKey().equalsIgnoreCase("Term Insurance"))
					{
						//call method to register for terminsurance
						buyTermInsurance(agentSales,policySales,PoliciesAndRidersAvail, lhmListOfAgents);
						
						break;
					}
					if(perObject.getKey().equalsIgnoreCase("Term Health Insurance"))
					{
						//call method to register for  term health insurance
						buyTermHealthInsurance(agentSales, policySales, PoliciesAndRidersAvail, lhmListOfAgents);
						break;
					}
				}
				i1++;
			}
			break;
		
		}
		
	}
	

	public void buyTermInsurance(LinkedHashMap<String, LinkedHashMap<String, basepolicyDO>>  agentSales,
			LinkedHashMap<String, basepolicyDO> policySales, 
			LinkedHashMap<String, ArrayList<String>> PolicyAndRiders, 
			LinkedHashMap<String, agentDO> lhmListOfAgents)
	{
		int i1 =1;
		System.out.println("Here is the list of agents selling this policy. Select and enter one ID.\nAgent ID: ");
		for(Entry<String, agentDO> perAgent: lhmListOfAgents.entrySet()){
			System.out.println(i1+")"+perAgent.getKey());
			i1++;
		}
		String agentSeq = t.next();
		t.nextLine();
		
		String partySeqStart = "PS";
		@SuppressWarnings("static-access")
		String partySeqEnd = String.valueOf(sequenceGenerator.getInstance().partySeq++);
		String partySeq = partySeqStart+partySeqEnd;
		
		System.out.println("Party sequence is generated automatically:\nParty Sequence ID:"+partySeq);
		
		String partyName;
		while(true){
		System.out.print("Enter your name: ");
		partyName = t.nextLine();
		if (partyName.matches("[a-zA-Z]+")) {
			System.out.println("Valid input");
			break;
		} else {
			System.out.println("Invalid input. Please enter only alphabets. \n Try again ");
			continue;
		}
		}
		
		double SumAssuredAmt=0;
		while(true){
		//Asking for sum assured
		System.out.println("At what cost do you want to buy a policy?\n" +
				"1)1 Crore\n" +
				"2)2 Crore\n" +
				"3)3 Crore\n" +
				"4)4 Crore\n" +
				"5)5 Crore");
		
		int policyCost = t.nextInt();
		switch(policyCost){
		case 1:
			SumAssuredAmt=10000000;
			break;
		case 2:
			SumAssuredAmt=20000000;
			break;
		case 3:
			SumAssuredAmt=30000000;
			break;
		case 4:
			SumAssuredAmt=40000000;
			break;
		case 5: 
			SumAssuredAmt=50000000;
			break;
		default: System.out.println("Kindly enter a valid input");
				continue;
		}
		break;
		}
		
		//generate policy seq
		String policySeqStart = "POLS";
		String policySeqEnd = String.valueOf(sequenceGenerator.getInstance().policySeq++);
		String policySeq = policySeqStart+policySeqEnd;

		int numOfRiders=0;
		
		while(true){
		System.out.println("Do you want to add any riders?\n1)Yes\n2)No");
		List <RiderDO> listOfRiders = new ArrayList<RiderDO>();
		int usersWish = t.nextInt();
		switch(usersWish)
		{
		case 1:

			RiderDO riderDoObject = new RiderDO();
			//getting list of rider
			ArrayList<String> termInsRiders = new ArrayList<String>();
			for(Entry<String, ArrayList<String>> perObject: PolicyAndRiders.entrySet())
			{
				if(perObject.getKey().equalsIgnoreCase("Term Insurance"))
				{
					termInsRiders=perObject.getValue();
				}
			}
			//Displaying riders
			int i3=1;
			for(String perRider: termInsRiders)
			{
				System.out.println(i3+") "+perRider);
				i3++;
			}
			int userRider= t.nextInt();
			
			//Setting Rider name
			switch(userRider){
			case 1:
				riderDoObject.setRiderName("Critical illness benefit rider");
				break;
			case 2:
				riderDoObject.setRiderName("Accidental death benefit rider");
				break;
			case 3:
				riderDoObject.setRiderName("Terminal illness benefit rider");
				break;
			case 4:
				riderDoObject.setRiderName("Permanent disability benefit rider");
				break;
			default: 
				System.out.println("Kindly enter a correct value");
			}
			
			//RiderSequence
			String riderSeqStart = "RS";
			String riderSeqEnd = String.valueOf(sequenceGenerator.getInstance().riderSeq++);
			String riderSeq = riderSeqStart+riderSeqEnd;
			riderDoObject.setRiderSeq(riderSeq);
			System.out.println("Rider Sequence is generated automatically:\nRider Sequence:"+riderDoObject.getRiderSeq());
			
			//Cover amount
			double Coveramt = SumAssuredAmt*0.02;
			riderDoObject.setCoverAmount(Coveramt);
			System.out.println("Cover amount of rider: "+riderDoObject.getCoverAmount());
			
			//Description
			riderDoObject.setRiderDescription("Bohot acha h lelo");
			
			listOfRiders.add(riderDoObject);
			numOfRiders++;
			
			System.out.println("Do you want to add another rider?\n1)Yes\n2)No\nEnter your choice: ");
			int userChoice = t.nextInt();
			t.nextLine();
			
			if(userChoice==1){
				continue;
			}else if(userChoice==2){
				termInsureanceDO termInsObject1 = new termInsureanceDO(policySeq, SumAssuredAmt*0.02, numOfRiders, SumAssuredAmt, listOfRiders, agentSeq, partySeq, partyName);
				System.out.println("Premium amount: "+termInsObject1.getPremiumAmt()+"\nSum Assured: "+termInsObject1.getSumAssured()+"\nNumber of Riders: "+numOfRiders);
				policySales.put(policySeq,termInsObject1);
				agentSales.put(agentSeq, policySales);
				break;
			}else{
				System.out.println("Kindly give a valid input");
				//continue;
			}
			break;
			
		case 2://create object using parameterized constructor
			termInsureanceDO termInsObject2 = new termInsureanceDO(policySeq, SumAssuredAmt*0.02, numOfRiders, SumAssuredAmt, listOfRiders, agentSeq, partySeq, partyName);
			System.out.println("Premium amount: "+termInsObject2.getPremiumAmt()+"\nSum Assured: "+termInsObject2.getSumAssured()+"\nNumber of Riders: "+numOfRiders);
			policySales.put(policySeq,termInsObject2);
			agentSales.put(agentSeq, policySales);
			
			for(Entry<String, basepolicyDO> one: policySales.entrySet())
			{
				System.out.println("Policy seq: "+one.getKey()+"Object type: "+one.getValue().getAgentSeq());
			}
			break;
			
		default: 
			System.out.println("Enter a valid input");
			continue;
		}
		break;
		}
		
		
	}
	
	public void buyTermHealthInsurance(
			LinkedHashMap<String, LinkedHashMap<String, basepolicyDO>> agentSales,
			LinkedHashMap<String, basepolicyDO> policySales,
			LinkedHashMap<String, ArrayList<String>> PolicyAndRiders,
			LinkedHashMap<String, agentDO> lhmListOfAgents) 
 {

		// Listing agents and getting agent sequence
		int i1 = 1;
		System.out
				.println("Here is the list of agents selling this policy. Select and enter one ID.\nAgent ID: ");
		for (Entry<String, agentDO> perAgent : lhmListOfAgents.entrySet()) {
			System.out.println(i1 + ")" + perAgent.getKey());
			i1++;
		}
		String agentSeq = t.nextLine();
		t.nextLine();

		// Party sequence
		String partySeqStart = "PS";
		String partySeqEnd = String
				.valueOf(sequenceGenerator.getInstance().partySeq++);
		String partySeq = partySeqStart + partySeqEnd;
		
		System.out.println("Party sequence is generated automatically.\nParty sequence: "+partySeq);

		// Party Name
		String partyName;
		while (true) {
			System.out.print("Enter your name: ");
			partyName = t.nextLine();
			if (partyName.matches("[a-zA-Z]+")) {
				System.out.println("Valid input");
				break;
			} else {
				System.out
						.println("Invalid input. Please enter only alphabets. \nTry again ");
				continue;
			}
		}

		// Getting the sum assured
		double SumAssuredAmt = 0;
		while (true) {
			// Asking for sum assured
			System.out.println("At what cost do you want to buy a policy?\n"
					+ "1)1 Crore\n" + "2)2 Crore\n" + "3)3 Crore\n"
					+ "4)4 Crore\n" + "5)5 Crore");

			int policyCost = t.nextInt();
			switch (policyCost) {
			case 1:
				SumAssuredAmt = 10000000;
				break;
			case 2:
				SumAssuredAmt = 20000000;
				break;
			case 3:
				SumAssuredAmt = 30000000;
				break;
			case 4:
				SumAssuredAmt = 40000000;
				break;
			case 5:
				SumAssuredAmt = 50000000;
				break;
			default:
				System.out.println("Kindly enter a valid input");
				continue;
			}
			break;
		}
		
		double healthCover = SumAssuredAmt*0.05;

		// generate policy seq
		String policySeqStart = "PS";
		String policySeqEnd = String.valueOf(sequenceGenerator.getInstance().agentSeq++);
		String policySeq = policySeqStart + policySeqEnd;

		int numOfRiders = 0;

		while (true) {
			System.out.println("Do you want to add any riders?\n1)Yes\n2)No");
			List<RiderDO> listOfRiders = new ArrayList<RiderDO>();
			int usersWish = t.nextInt();
			switch (usersWish) {
			case 1:

				RiderDO riderDoObject = new RiderDO();
				// getting list of rider
				ArrayList<String> termHealthInsRiders = new ArrayList<String>();
				for (Entry<String, ArrayList<String>> perObject : PolicyAndRiders
						.entrySet()) {
					if (perObject.getKey().equalsIgnoreCase(
							"Term Health Insurance")) {
						termHealthInsRiders = perObject.getValue();
					}
				}
				// Displaying riders
				int i3 = 1;
				for (String perRider : termHealthInsRiders) {
					System.out.println(i3 + ") " + perRider);
					i3++;
				}
				int userRider = t.nextInt();

				// Setting Rider name
				switch(userRider){
				case 1:
					riderDoObject.setRiderName("Critical illness rider");
					break;
				case 2:
					riderDoObject.setRiderName("OPD rider");
					break;
				case 3:
					riderDoObject.setRiderName("Maternity rider");
					break;
				case 4:
					riderDoObject.setRiderName("Room rent waiver");
					break;
				case 5:
					riderDoObject.setRiderName("Personal accident rider");
					break;
				case 6:
					riderDoObject.setRiderName("Global cover rider");
					break;
				default:
					System.out.println("Kindly enter a correct input");
				}

				// RiderSequence
				String riderSeqStart = "RS";
				String riderSeqEnd = String.valueOf(sequenceGenerator.getInstance().riderSeq++);
				String riderSeq = riderSeqStart + riderSeqEnd;
				riderDoObject.setRiderSeq(riderSeq);
				System.out
						.println("Rider Sequence is generated automatically:\nRider Sequence:"
								+ riderDoObject.getRiderSeq());

				// Cover amount
				double Coveramt = SumAssuredAmt * 0.02;
				riderDoObject.setCoverAmount(Coveramt);
				System.out.println("Cover amount of rider: "
						+ riderDoObject.getCoverAmount());

				// Description
				riderDoObject.setRiderDescription("Bohot acha h lelo");

				listOfRiders.add(riderDoObject);
				numOfRiders++;

				System.out.println("Do you want to add another rider?\n1)Yes\n2)No\nEnter your choice: ");
				int userChoice = t.nextInt();
				if (userChoice == 1) {
					continue;
				} else if (userChoice == 2) {
					break;
				} else {
					System.out.println("Kindly give a valid input");
				}
				
				termHealthInsuranceDO termHealthObject1 = new termHealthInsuranceDO(agentSeq, partySeq, partyName, policySeq, SumAssuredAmt, healthCover, listOfRiders, numOfRiders );
				//THI constructor pass values
				System.out.println("Premium: "+termHealthObject1.getPremiumAmt()+"\n Sum Assured: "+termHealthObject1.getSumAssured()+"\nNumber of Riders: "+numOfRiders);
				policySales.put(policySeq, termHealthObject1);
				agentSales.put(agentSeq, policySales);
				break;
				
			case 2:
				//THI constructor pass values
				termHealthInsuranceDO termHealthObject2 = new termHealthInsuranceDO(agentSeq, partySeq, partyName, policySeq, SumAssuredAmt, healthCover, listOfRiders, numOfRiders );
				System.out.println("Premium: "+termHealthObject2.getPremiumAmt()+"\nSum Assured: "+termHealthObject2.getSumAssured()+"\nHealth Cover: "+termHealthObject2.getHealthCover()+"\nNumber of Riders: "+numOfRiders);
				policySales.put(policySeq, termHealthObject2);
				agentSales.put(agentSeq, policySales);
				break;

			default:
				System.out.println("Enter a valid input");
				continue;
			}
			break;
		}

	}
	
}

package operations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import DataObjects.CommTransactionDO;
import DataObjects.RiderDO;
import DataObjects.agentDO;
import DataObjects.basepolicyDO;

public class Main_driver_class {
	
	public static void main(String args[]) throws Exception{
		agentDO director = new agentDO("DR1", "DIRECTOR", "NONE", "Female", "Miss", "Shruti", "Jagtap");
		agentDO agent_manager = new agentDO("AM1", "AGENT MANAGER","DR1", "Male", "Mr.", "Harry", "Styles");
		agentDO agent1 = new agentDO("AGT9", "AGENT", "AM1", "MALE", "Mr.", "Shreya", "Jagtap");
		
		LinkedHashMap<String, agentDO> lhmListOfAgents = new LinkedHashMap<String, agentDO>();
		lhmListOfAgents.put("DR1", director);
		lhmListOfAgents.put("AM1", agent_manager);
		lhmListOfAgents.put("AGT9", agent1);
		
		List<CommTransactionDO> CommTransList = new ArrayList<CommTransactionDO>();
		
		LinkedHashMap<String, Integer> designations = new LinkedHashMap<String, Integer>();
		designations.put("DIRECTOR", 100);
		designations.put("AGENT MANAGER", 70);
		designations.put("AGENT", 20);
		
		LinkedHashMap<String,LinkedHashMap<String,basepolicyDO>> AgentSales=
				new LinkedHashMap<String,LinkedHashMap<String,basepolicyDO>>();
		
		LinkedHashMap<String,basepolicyDO> PolicySales=new LinkedHashMap<String,basepolicyDO>();
		
		LinkedHashMap<String,List<CommTransactionDO>> LHMCommData=
				new LinkedHashMap<String,List<CommTransactionDO>>();
		
		Scanner myInput = new Scanner(System.in);
	
		while(true){
			System.out.println("---- Welcome to the portal ----");
			System.out.println("What do you wish to do?\n1) Add a new agent" +
					"\n2) Edit the crendetials of an existing agent" +
					"\n3) Delete an agent" +
					"\n4) Display existing ones" +
					"\n5) Search by agent ID" +
					"\n6) Download file of an agent by ID"+
					"\n7) Buy a policy"+
					"\n8) Pay premium"+
					"\n9) Commission Data"+
					"\n10) Exit"+
					"\n11) Agent Client Summary"+
					"\n12) All commissions issued within given time frame"+
					"\nKindly enter a value respective to your choice.");
			int opt = myInput.nextInt();
			myInput.nextLine();
			switch(opt){
			case 1://Add a new agent
				agentDO agentToRegiter = new agentDO();
				System.out.println("Kindly enter the following information:");
				onBoardingClass newAgent = new onBoardingClass();
				newAgent.register_Agent(agentToRegiter, lhmListOfAgents, designations);
				lhmListOfAgents.put(agentToRegiter.getAgentSeq(), agentToRegiter);
				break;
				
			case 2://Edit the credentials of an existing agent
				System.out.println("Here is the list of already existing agents");
				for(Entry<String, agentDO> perAgent: lhmListOfAgents.entrySet()){
					System.out.println("ID: "+perAgent.getValue().getAgentSeq()+" Name: "+perAgent.getValue().getAgentFirstName()+" "+perAgent.getValue().getAgentLastName());
				}
				System.out.println("Enter the ID of the one you want to edit");
				String agentToEditID = myInput.nextLine();
				edit_details editDetailsObj = new edit_details();
				editDetailsObj.editPersonalDetails(agentToEditID, lhmListOfAgents,designations);
				break;
				
			case 3://Delete an agent
				System.out.println("Available agents: ");
				for(Map.Entry<String, agentDO> perLHM: lhmListOfAgents.entrySet()){
					System.out.println("ID: "+perLHM.getKey()+" Name: "+perLHM.getValue().getAgentFirstName());
				}
				System.out.println("Enter the ID of the one you wish to delete");
				String agentTODelete = myInput.nextLine().toUpperCase();
				lhmListOfAgents.remove(agentTODelete);
				break;
				
			case 4://Displaying all the existing employees
				for(Entry<String, agentDO> perAgent: lhmListOfAgents.entrySet()){
					String print = perAgent.getValue().toString();
					System.out.println(print);
				}
				break;
				
			case 5://Searching for details of an agent
				System.out.print("Enter the ID of the agent you are looking for: ");
				String desiredAgentID  = myInput.next();
				
				String corrected = desiredAgentID.toUpperCase();
				System.out.println(lhmListOfAgents.get(corrected).toString());
				break;
				
			case 6://Download file of an agent by ID
				for(Entry<String, agentDO> perAgent: lhmListOfAgents.entrySet()){
					System.out.println(perAgent.getValue().toString());
				}
				System.out.println("Enter ID of the agent you want to download the details of: ");
				String agentIdToDownload = myInput.nextLine().toUpperCase();
				DownloadFile newObjectDown = new DownloadFile();
				newObjectDown.DownloadUserFile(agentIdToDownload, lhmListOfAgents);
				break;
				
			case 7: //Buying a policy
				buyingPolicy buyPol = new buyingPolicy();
				buyPol.ListingPolicyAndRiders(AgentSales,PolicySales, lhmListOfAgents,LHMCommData);
				break;
				
			case 8://Paying the premium
				System.out.println("---- Welcome to payment module ----");
				for(Entry<String, agentDO> perAgent: lhmListOfAgents.entrySet()){
					String agentSeq = perAgent.getKey();
					LHMCommData.put(agentSeq, CommTransList);
				}
				System.out.println("Enter you party Sequence ID: ");
				String partySeq = myInput.next();
				
				System.out.println("Do you want to pay the premium amount?\n1)Yes\n2)No");
				int userChoice = myInput.nextInt();
				
				switch(userChoice){
				case 1:
					CommissionClass CommClassObject = new CommissionClass();
					CommClassObject.CommRollout(AgentSales, PolicySales, lhmListOfAgents, LHMCommData, partySeq);
					break;
					
				case 2:
					System.out.println("Your policy has been terminated");
					break;
				default: 
					System.out.println("Kindly enter a valid input");
					break;
				}
				break;
				
			case 9://Display Comission data
				System.out.print("Enter the agent sequence for the agent whose commission transaction you wish to view: ");
				String agentSeq = myInput.next();
				
				viewAgentCommissionData viewObject = new viewAgentCommissionData();
				viewObject.viewCommissionData(LHMCommData, agentSeq );
				break;
				
			case 10:
				System.out.println("Your program has been terminated");
				break;
				
			case 11: //Agent Client Summary
				System.out.println("Here is the list of agents and the clients he has sold policies to :");
				agentClientSummary ACSObject = new agentClientSummary();
				ACSObject.agentAndClientSummary(AgentSales);
				break;
				
			case 12: //All commissions issued within given time frame
				AllCommissions AllCommObject = new AllCommissions();
				AllCommObject.viewAllCommissions(LHMCommData);
				break;
				
			default: System.out.println("Kindly enter a valid input");
					continue;
			}
		}
	}

}

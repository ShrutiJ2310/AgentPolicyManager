package operations;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import DataObjects.agentAddressDO;
import DataObjects.agentContactDO;
import DataObjects.agentDO;

public class onBoardingClass {
	static Scanner sc = new Scanner(System.in);
	
	//Method that checks date does not belong to the future
	public static boolean isDateNotInFuture(String dateString) {
        if (dateString == null) {
            throw new IllegalArgumentException("Date string cannot be null");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date inputDate = formatter.parse(dateString);
            Date currentDate = new Date();
            return !inputDate.after(currentDate); // Returns true if not in the future
        } catch (ParseException e) {
            return false; // or handle as needed
        }
    }
	
	//String to date method
	public static Date StringToDate(String dateString){
		SimpleDateFormat inputFormatter = new SimpleDateFormat("dd-MM-yyyy");
     
        try {
            // Parse the string into a Date object
            Date date = inputFormatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	
	//Methods that calculates age
	public static int calculateAge(String dobStr) {
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Parse the date of birth from the string
            Date dob = dateFormat.parse(dobStr);

            // Get the current date
            Calendar today = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(dob);

            // Calculate the age in years
            int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

            // Adjust the age if the birthday hasn't occurred yet this year
            if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
                age--;  // Not yet had a birthday this year
            }

            return age;

        } catch (ParseException e) {
            // Handle invalid date format
            System.err.println("Invalid date format. Please use dd-MM-yyyy.");
            return -1;  // Return -1 to indicate an error
        }
	}
	
	public void register_Agent(agentDO agent, LinkedHashMap<String , agentDO> list_agents, 
			LinkedHashMap<String, Integer> designations) 
	{

		// Agent ID
		System.out.println("Agent ID will be set automatically");
		String AgentSeqstart = "AGT";
		String num = String.valueOf(sequenceGenerator.getInstance().agentSeq++);
		String Agentseq = AgentSeqstart + num;
		agent.setAgentSeq(Agentseq);
//------------------------------------------------------------------------------------------------
	
		// title
		while (true) {
			System.out.println("Title\n1)Miss\n2)Mrs\n3)Mr");
			int title = sc.nextInt();
			sc.nextLine();
			if (title==1){
				agent.setTitle("Miss");
				break;
			} else if(title==2) {
				agent.setTitle("Mrs");
				break;
			}else if(title==3){
				agent.setTitle("Mr");
				break;
			}else{
				System.out.println("Please enter a valid title input");
				continue;
			}
		}

		// Gender
				while (true) {
					if(agent.getTitle()=="Miss" || agent.getTitle()=="Mrs"){
						agent.setGenderCd("Female");
						break;
					}else{
						agent.setGenderCd("Male");
						break;
					}
				}
				System.out.println("Gender: "+agent.getGenderCd());
				
		// First name
		while (true) {
			System.out.println("First name: ");
			String fN = sc.nextLine();
			if (fN.matches("[a-zA-Z]+")) {
				agent.setAgentFirstName(fN);

				break;
			} else {
				System.out
						.println("Invalid input. Please enter only alphabets. \nTry again ");
				continue;
			}
		}

		// Last name
		while (true) {
			System.out.println("Last name: ");
			String lN = sc.nextLine();
			if (lN.matches("[a-zA-Z]+")) {
				agent.setAgentLastName(lN);
				System.out.println(agent.getAgentFirstName());
				break;
			} else {
				System.out
						.println("Invalid input. Please enter only alphabets. \nTry again");
				continue;
			}
		}

		
		//Designation of the agent
		while (true) {
			System.out.println("Choose your designation designation:\n"
					+ "1)Director\n" + "2)Agent Manager\n" + "3)Agent\n"
					+ "Select your position and enter a number ");
			int des = sc.nextInt();
			sc.nextLine();
			if (des == 1) {
				agent.setDesignationCd("DIRECTOR");
				break;
			} else if (des == 2) {
				agent.setDesignationCd("AGENT MANAGER");
				break;
			} else if (des == 3) {
				agent.setDesignationCd("AGENT");
				break;
			} else {
				System.out.println("Please enter a valid input");
				continue;
			}
		}
		
		// Reporting manager of the agent wanting to register
		// Displaying the existing values to report to
		while (true) {
			int i = 1;
			System.out.println("Who will you be reporting to?");
			for (Map.Entry<String, agentDO> perAgent : list_agents.entrySet()) {
				System.out.println(i+")ID: " + perAgent.getValue().getAgentSeq()
						+ " Name: " + perAgent.getValue().getAgentFirstName());
				i++;
			}
			System.out.println("Select a number");
			int reportingNum = sc.nextInt();
			sc.nextLine();
			// Validating correct input
			if (reportingNum < 1 || reportingNum >= i) {
				System.out.println("Invalid selection. Please try again");
				continue;
			}

			int j = 1;
			boolean validSelection = false;
			for (Map.Entry<String, agentDO> perAgents : list_agents.entrySet()) {
				agentDO reportingAgentObj = perAgents.getValue();

				// Trying to find the reporting agent
				if (reportingNum == j) {
					String reportingDesig = reportingAgentObj
							.getDesignationCd();
					int repDesignationValue = designations.get(reportingDesig);
					int agentDesignationValue = designations.get(agent
							.getDesignationCd());
					// Comparing the designation levels before setting
					if (repDesignationValue > agentDesignationValue) {
						agent.setReportsTo(reportingAgentObj.getAgentSeq());
						validSelection = true; // Marking the selection as a
												// valid selection
					} else {
						System.out.println("You cannot report to a Junior. Please try again");
					}
					break;
				}
				j++;
			}

			// If the selection was valid, break the loop
			if (validSelection) {
				break;
			}
		}

		//License date
		while (true) {
			System.out.println("Licence issue date(in dd-mm-yyyy format): ");
			String dateString = sc.nextLine();
			Date licenceDate = StringToDate(dateString);
			boolean val = isDateNotInFuture(dateString);

			if (val == true) {
				System.out.println("You are eligible.");
				agent.setLicenceIssueDate(licenceDate);
				break;
			} else {
				System.err.println("Licence date cannot belong to the future. Please try again ");
				continue;
			}
		}
		
		while(true){
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    String dateInString=formatter.format(agent.getLicenceIssueDate());
			int valPeriod = calculateAge(dateInString);
			
			// Validity Period
			if (valPeriod > 1) {
				System.out
						.println("You are eligible to register. You validity period last for "
								+ valPeriod + " years");
				agent.setValidityPeriod(valPeriod);
				break;
			}else{
			System.out.println("Error! Your validity period should be for more than a year. Please try again");
			break;
			}
		
		}
	
		
		//Channel
		while(true){
			System.out.println("Which channel do you prefer?\n1) Agency\n2) Bankassurance\n3) Broker\nEnter a number with respect to your desired option");
			int channelNo = sc.nextInt();
		switch(channelNo){
		case 1:
			agent.setChannelCd("Agency");
			break;
		case 2:
			agent.setChannelCd("Bankassurance");
			break;
		case 3:
			agent.setChannelCd("Broker");
			break;
		default: 
			System.out.println("Kindly enter a valid option");
			continue;
		}
		break;
		}
		
		getAgentAddress(agent, list_agents);
	}	
	//--------------------------------------------------------------------------------------------
		private void getContact(agentDO agent,LinkedHashMap<String, agentDO> listOfAgents) 
		{
			while (true) {
				agentContactDO contactObj = new agentContactDO();
				System.out.print("Contact sequence will be generated automatically\nContact Sequence: ");
				String addressSeqStart = "CNT";
				String number = String.valueOf(sequenceGenerator.getInstance().contactSeq++);
				contactObj.setContactSeq(addressSeqStart + number);
				System.out.print(contactObj.getContactSeq());

				System.out.println("\nContact type:\n1)Mobile\n2)Residential\n3)Office\nChoose one and key in a number");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					contactObj.setContactType("Mobile");
					break;
				case 2:
					contactObj.setContactType("Residential");
					break;
				case 3:
					contactObj.setContactType("Office");
					break;
				default:
					System.out.println("Please enter a valid choice");
					continue;
				}

				System.out.print("\nContact number: ");
				String conNum = sc.next();
				int len = conNum.length();
				if (len == 10 || len == 8) {
					System.out.println("Contact has been set successfully");
					contactObj.setContactNum(conNum);
					agent.list_agentContactDO.add(contactObj);
					System.out.println("Do you want to add another contact?\n1)Yes\n2)No");
					int ans = sc.nextInt();
					if (ans == 1){
						agent.getList_agentContactDO().add(contactObj);
						continue;
					}else{
						agent.getList_agentContactDO().add(contactObj);
						break; }
				} else {
					System.out.println("Kindly enter a valid mobile number");
					continue;
				}
				
			}
		}

		//Address Details
	public  void getAgentAddress(agentDO agent, LinkedHashMap<String, agentDO> listOfAgents) {
		boolean flags = true;
		while (flags)
		{
			// Address Sequence and type
			agentAddressDO AddressObject = new agentAddressDO();
			System.out
					.print("Address Sequence will be set automaticcally\nAddress Sequence: ");
			String AddSeqFirstHalf = "ADD";
			String ber = String
					.valueOf(sequenceGenerator.getInstance().agentAddressSeq++);
			AddressObject.setAgentAddressSeq(AddSeqFirstHalf + ber);
			System.out.println(AddressObject.getAgentAddressSeq());
			System.out
					.println("What is the type of address?\n1)Residential\n2)Office\n3)Other");
			int AddTypeOpt = sc.nextInt();
			switch (AddTypeOpt) {
			case 1:
				AddressObject.setAddressType("Residential");
				break;
			case 2:
				AddressObject.setAddressType("Office");
				break;
			case 3:
				AddressObject.setAddressType("Other");
				break;
			default:
				System.out.println("Kindly give a valid input");
				continue;
			}

			// Country and states
			HashMap<String, ArrayList<String>> countriesStates = new HashMap<String, ArrayList<String>>();
			
			ArrayList<String> indiaStates = new ArrayList<String>();
			indiaStates.add("Maharashtra");
			indiaStates.add("West Bengal");
			indiaStates.add("Tamil Nadu");
			indiaStates.add("Uttar Pradesh");
			
			ArrayList<String> USAStates = new ArrayList<String>();
			USAStates.add("Illinois");
			USAStates.add("Texas");
			USAStates.add("Florida");
			USAStates.add("New York");
			
			ArrayList<String> australiaStates = new ArrayList<String>();
			australiaStates.add("New south wales");
			australiaStates.add("Victoria");
			australiaStates.add("Queensland");
			australiaStates.add("Tasmania");
			
			countriesStates.put("India",indiaStates);
			countriesStates.put("United states of America", USAStates);
			countriesStates.put("Australia", australiaStates);
			// Displaying the countries
			System.out.println("List of countries");
			int j = 1;
			for (Map.Entry<String, ArrayList<String>> perCountry : countriesStates
					.entrySet()) {
				System.out.println(j + ") " + perCountry.getKey());
				j++;
			}
			System.out.print("Enter your choice: ");
			int userCountry = sc.nextInt();

			//getting states of countries
			j = 1;
			ArrayList<String> states = null;
			for (Map.Entry<String, ArrayList<String>> perCountry : countriesStates.entrySet()) {
				if (j == userCountry) {
					AddressObject.setCountryCd(perCountry.getKey());
					states = perCountry.getValue();
					break;
				} 
				j++;
			}
			//printing the states
			int p = 1;
			for(String var: states){
				System.out.println(p+") "+var);
				p++;
			}
			int userStatesChoice = sc.nextInt();
			sc.nextLine();
			p=1;
			for(String var: states)
			{
				if(p==userStatesChoice)
				{
					AddressObject.setStateCd(var);
					break;
				}
				p++;
			}

			// Pin Code
			System.out.print("Pincode: ");
			String pinCode = sc.nextLine();
			AddressObject.setPinCode(pinCode);

			// Details of address
			System.out.println("Enter other details(Such as flat number, Wing): ");
			String AddressDetails = sc.nextLine();
			AddressObject.setAddressDetails(AddressDetails);

			System.out.println("Do you want to make this address a primary one?\n1)Yes\n2)No\nEnter you choice");
			int choice = sc.nextInt();
			if (choice == 1) 
			{
				AddressObject.setPrimaryAddress("Yes");

			} else 
			{
				AddressObject.setPrimaryAddress("No");
			}

			agent.getList_agentAddressDO().add(AddressObject);

			System.out.println("Do you want to enter another address?\n1)Yes\n2)No\nEnter your choice: ");
			int userChoice = sc.nextInt();
			if (userChoice == 1) 
			{
				continue;
			} else 
			{
				if(checkPrimary(agent))
				{
					System.out.println("All good");
					getContact(agent, listOfAgents);
					break;
				}
				else
				{
					System.out.println("at least one of the address should be primary");
					setPrimary(agent);
				}
			}
			agent.getList_agentAddressDO().add(AddressObject);
			getContact(agent, listOfAgents);
			break;
		}

	}
	
		void setPrimary(agentDO agent) 
		{
			Scanner t = new Scanner(System.in);
			int i=1;
			for(agentAddressDO var: agent.getList_agentAddressDO()){
				System.out.println(i+") "+var.getAgentAddressSeq());
				i++;
			}
			int userChoice = t.nextInt();
			int count = 1;
			for(agentAddressDO var: agent.getList_agentAddressDO()){
				if(userChoice==count){
					var.setPrimaryAddress("Yes");
					break;
				}else{
					count++;
				}
			}
			
		}

		boolean checkPrimary(agentDO agentToCheck)
		{
				int countNo = 0;
				for (agentAddressDO var : agentToCheck.getList_agentAddressDO()) 
				{
					if (var.getPrimaryAddress().equalsIgnoreCase("Yes")) 
					{
						return true;
					}
					else
					{
						countNo++;
						continue;
					}
				}
				return false;
		}
		
		
	}




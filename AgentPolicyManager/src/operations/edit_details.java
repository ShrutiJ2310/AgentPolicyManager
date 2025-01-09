package operations;

import java.sql.PseudoColumnUsage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import DataObjects.agentAddressDO;
import DataObjects.agentContactDO;
import DataObjects.agentDO;

public class edit_details {
	Scanner t = new Scanner(System.in);
	
	public void editDetailsOfAgent(String agentIdToEdit, LinkedHashMap<String, agentDO> listOfAgents, LinkedHashMap<String, Integer> hirarchy){
		System.out.println("What do you want to edit?\n1)Personal details\n2)Address details\n3)Contact details");
		agentDO agentToEdit = listOfAgents.get(agentIdToEdit);
		int userChoice = t.nextInt();
		switch(userChoice){
		case 1:
			editPersonalDetails(agentIdToEdit, listOfAgents, hirarchy);
			break;
		case 2:
			editAddressDetails(agentToEdit, listOfAgents);
			break;
		case 3:
			editContactDetails(agentToEdit, listOfAgents);
			break;
		default: System.out.println("Kindly enter a valid input");
		}
	}
	
	//Edits the contact details of an already existing agent
	private void editContactDetails(agentDO agentToEdit, LinkedHashMap<String, agentDO> listOfAgents) {
		List<agentContactDO> contactDetails = agentToEdit.getList_agentContactDO();
		System.out.println("List of existing contacts.");
		
		for(agentContactDO perContact: contactDetails){
			System.out.println("ID: "+perContact.getContactSeq()+" Type: "+perContact.getContactType()+" Number: "+perContact.getContactNum());
		}
		System.out.println("Enter the ID of the one you need to edit - ");
		String contacIdToEdit = t.next();
		
		agentContactDO contactToedit = new agentContactDO();
		for(agentContactDO perContact: contactDetails){
			if(perContact.getContactSeq().equalsIgnoreCase(contacIdToEdit)){
				contactToedit = perContact;
			}
		}
		
		System.out.println("What do you want to edit in this contacts?\n1)Type of contact\n2)Contact number");
		int userChoice = t.nextInt();
		
		switch(userChoice){
		case 1:
			System.out.println("Current type of contact: "+contactToedit.getContactType());
			System.out.println("What do you want to set it to?\n1)Mobile\n2)Residential\n3)Office\nEnter your choice: ");
			int n = t.nextInt();
			if(n==1){
				contactToedit.setContactType("Mobile");
				break;
			}else if(n==2){
				contactToedit.setContactType("Residential");
				break;
			}else if(n==3){
				contactToedit.setContactType("Office");
				break;
			}else{
				System.out.println("Enter a valid input");
			}
			break;
		case 2:
			System.out.println("Current contact: "+contactToedit.getContactNum()+"\nEnter new number: ");
			String newNumber = t.next();
			int len = newNumber.length();
			if (len == 10 || len == 8) {
				System.out.println("Contact has been set successfully");
				contactToedit.setContactNum(newNumber);
				agentToEdit.list_agentContactDO.add(contactToedit);
				System.out.println("Do you want to add another contact?\n1)Yes\n2)No");
				int ans = t.nextInt();
				if (ans == 1){
					agentToEdit.getList_agentContactDO().add(contactToedit);
					
				}else{
					agentToEdit.getList_agentContactDO().add(contactToedit);
					break; }
			} else {
				System.out.println("Kindly enter a valid mobile number");
				
			}
			
			break;
		default: System.out.println("Enter a valid input");
		}
	
	}

	public static agentDO getDesiredAgent(String agentID, LinkedHashMap<String, agentDO> list_agents){
		agentDO agentToReturn = new agentDO();
		for(Entry<String, agentDO> perAgent: list_agents.entrySet()){
			if(agentID.equalsIgnoreCase(perAgent.getKey())){
				agentToReturn = perAgent.getValue();
			}
		}
		return agentToReturn;	
	}
	
	public static boolean checkName(String name){
		if (name.matches("[a-zA-Z]+")) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks that the given date does not belong in the future
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
	
	//that calculates age from give date in string format
	public static int calculateAge(String dobStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dob = dateFormat.parse(dobStr);

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
	
	
	//compare for designation change
	public static boolean compare(agentDO agent, LinkedHashMap<String, Integer> designations ){
		String agentEditedDesig = agent.getDesignationCd();
		String reportingID = agent.getReportsTo();

		int desigValue=designations.get(agentEditedDesig), reportingValue = designations.get(reportingID);
	
		if(reportingValue>desigValue){
			return true;

		}else if(reportingValue==desigValue){
			System.out.println("You cannot report to someone on the same level as you. Kindly change who you report to");
			return false;
		}else{
			return false;
		}
	}
	
	//check reportingValidity after reporting change 
	public static boolean checkReportingValidity(agentDO agent,String reportingTo,
			LinkedHashMap<String, Integer> designations,LinkedHashMap<String, agentDO> listAgents)
	{
		if(designations.get(agent.getDesignationCd())<designations.get(listAgents.get(reportingTo).getDesignationCd()))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static agentAddressDO findAddressBySequence(List<agentAddressDO> addressList, String sequence) {
	        for (agentAddressDO address : addressList) {
	            if (address.getAgentAddressSeq() == sequence) {
	                return address;  // Return the found object
	            }
	        }
	        return null;  // Return null if not found
	    }
	
	//Personal details editing
	public void editPersonalDetails(String agentToEditID, LinkedHashMap<String, agentDO> listOfAgents, LinkedHashMap<String, Integer> designations) {
	
	System.out.println("What do you want to edit?\n1)First Name\n2)Last name\n3)Designation\n4)Reporting\n5)Title\n6)License issue date\n7)Channel\nEnter you choice: ");
	int choice = t.nextInt();
	t.nextLine();
	int userChoice;
	agentDO agentToEdit = listOfAgents.get(agentToEditID);
	
	switch(choice){
	case 1:
		while(true){
		agentToEdit=getDesiredAgent(agentToEditID, listOfAgents);
		
		System.out.println("Currrent name: "+agentToEdit.getAgentFirstName()+"\nEnter the name you want to change it to: ");
		String changedFN = t.nextLine();
		boolean value = checkName(changedFN);
		if(value==true){
			agentToEdit.setAgentFirstName(changedFN);
			System.out.println("You first name has been changed successfully");
			break;
		}else{
			System.out.println("There was an error. Please try again");
			continue;
		}
		}
		break;
		
	case 2:
		while(true){
		agentToEdit=getDesiredAgent(agentToEditID, listOfAgents);
		
		System.out.println("Currrent name: "+agentToEdit.getAgentLastName()+"\nEnter the name you want to change it to: ");
		String changedLN = t.nextLine();
		boolean val = checkName(changedLN);
		if(val==true){
			agentToEdit.setAgentLastName(changedLN);
			System.out.println("You last name has been changed successfully");
			break;
		}else{
			System.out.println("There was an error. Please try again");
			continue;
		}
		}
		break;
		
	case 3: //Changing the designation
		while(true){
		agentToEdit=getDesiredAgent(agentToEditID, listOfAgents);
		System.out.println("Current Designation: "+agentToEdit.getDesignationCd()+"\nWhat designation do you want to change it to?\n1)Director\n2)Agent manager\n3)Agent");
		userChoice = t.nextInt();
		switch(userChoice){
		case 1: agentToEdit.setDesignationCd("DIRECTOR");
			break;
		case 2: agentToEdit.setDesignationCd("AGENT MANAGER");
			break;
		case 3: agentToEdit.setDesignationCd("AGENT");
			break;
		default: 	
		}
		boolean answer =compare(agentToEdit, designations);
		if(answer==true){
			System.out.println("You do not need to change who you report to");
		}else{
			int i = 1;
			System.out.println("Who will you be reporting to?");
			for (Map.Entry<String, agentDO> perAgent : listOfAgents.entrySet()) {
				System.out.println("ID: " + perAgent.getValue().getAgentSeq()
						+ " Name: " + perAgent.getValue().getAgentFirstName());
				i++;
			}
			System.out.println("Select a number");
			int reportingNum = t.nextInt();
			// Validating correct input
			if (reportingNum < 1 || reportingNum >= i) {
				System.out.println("Invalid selection. Please try again");
				continue;
			}
		}
		}
		//break;
	case 4: //To change reporting
		while(true){
		System.out.println("Who do you want to report to?");
		int i=1;
		for(Map.Entry<String, agentDO> perAgent: listOfAgents.entrySet()){
			System.out.println(i+++")ID: "+perAgent.getValue().getAgentFirstName()+" "+perAgent.getValue().getAgentLastName()+" Designation: "+perAgent.getValue().getDesignationCd());
		}
		System.out.println("Enter a number with respect to your desired option");
		int uChoice = t.nextInt();
		int j=1;
		String desiredReporting="";
		
		for(Map.Entry<String, agentDO> perAgent: listOfAgents.entrySet()){
			if(uChoice==j){
				desiredReporting=perAgent.getValue().getAgentSeq();
			}
		}
		boolean answer=checkReportingValidity(agentToEdit, desiredReporting, designations, listOfAgents);
		if(answer==true){
			agentToEdit.setReportsTo(desiredReporting);
			break;
		}else{
			System.out.println("You need to report to someone higher than your own designation. Please try again");
			continue;
		}
	}
		break;
		
		case 5:// title
			System.out.println("What do you want to change you title to?\nCurrent title: "+agentToEdit.getTitle());
			while (true) {
				System.out.println("Title\n1)Miss\n2)Mrs\n3)Mr");
				int title = t.nextInt();
				t.nextLine();
				if (title == 1) {
					agentToEdit.setTitle("Miss");
					agentToEdit.setGenderCd("Female");
					break;
				} else if (title == 2) {
					agentToEdit.setTitle("Mrs");
					agentToEdit.setGenderCd("Female");
					break;
				} else if (title == 3) {
					agentToEdit.setTitle("Mr");
					agentToEdit.setGenderCd("Male");
					break;
				} else {
					System.out.println("Please enter a valid title input");
					continue;
				}
			}
			break;
			
	case 6://license
	
		while (true) {
			System.out.println("Current License date = "+agentToEdit.getLicenceIssueDate()+"\nWhat do you want to change it to? Enter the date in dd-mm-yyyy format");
			String LDtoChange = t.nextLine();
			
			Date licenceDate = StringToDate(LDtoChange);
			boolean valueOf = isDateNotInFuture(LDtoChange);

			if (valueOf == true) {
				System.out.println("You are eligible.");
				agentToEdit.setLicenceIssueDate(licenceDate);
				break;
			} else {
				System.err.println("Licence date cannot belong to the future. Please try again ");
				continue;
			}
		}
		break;
		
	case 7://channel
		System.out.println("Current channel: "+agentToEdit.getChannelCd());
		while(true){
			System.out.println("Which channel do you prefer?\n1) Agency\n2) Bankassurance\n3) Broker\nEnter a number with respect to your desired option");
			int channelNo = t.nextInt();
		switch(channelNo){
		case 1:
			agentToEdit.setChannelCd("Agency");
			break;
		case 2:
			agentToEdit.setChannelCd("Bankassurance");
			break;
		case 3:
			agentToEdit.setChannelCd("Broker");
			break;
		default: 
			System.out.println("Kindly enter a valid option");
			continue;
		}
		break;
		}
		break;
	default: System.out.println("Enter a valid input");
	}
	}
	
	//Method to edit address details
	public static void editAddressDetails(agentDO agentToEdit, LinkedHashMap<String, agentDO> listOfAgents)
	{
		Scanner t = new Scanner(System.in);
		System.out.println("Here is the list of your current addresses: ");
		List<agentAddressDO> addressObject = agentToEdit.getList_agentAddressDO();
		
		for (agentAddressDO perAddress : addressObject) {
			System.out.println(perAddress.getAgentAddressSeq());
		}
		System.out.println("Enter the address sequence of the address you want to edit");
		String addToEditSeq = t.nextLine();

		agentAddressDO addToEdit = findAddressBySequence(addressObject,addToEditSeq);
		
		System.out.println("What do you wish to change?\n1)Type of address\n2)Country\n3)State\n4)Details\n5)Pincode");
		int userChangeAddChoice = t.nextInt();
		switch (userChangeAddChoice) {
		case 1:
			while(true){

			System.out.println("Current type of address: "+addToEdit.getAddressType());
			System.out.println("What do you want to change it to?\n1)Residential\n2}Residential3)Other\nEnter your choice");
			int typeChange = t.nextInt();
			if(typeChange==1){
				addToEdit.setAddressType("Residential");
				break;
			}else if (typeChange==2){
				addToEdit.setAddressType("Office");
				break;
			}else if(typeChange==3){
				addToEdit.setAddressType("Other");
				break;
			}else{
				System.out.println("Enter a valid input");
				continue;
			}
		}
			break;
			
		case 2:
			System.out.println("Current country: "+addToEdit.getCountryCd());
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
			int userCountry = t.nextInt();

			// Displaying states of countries
			j = 1;
			ArrayList<String> states = null;
			for (Map.Entry<String, ArrayList<String>> perCountry : countriesStates.entrySet()) {
				if (j == userCountry) {
					addToEdit.setCountryCd(perCountry.getKey());
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
			int userStatesChoice = t.nextInt();
			t.nextLine();
			p=1;
			for(String var: states){
				if(p==userStatesChoice){
					addToEdit.setStateCd(var);
					break;
				}
				p++;
			}
			break;
			
		case 3://state
			System.out.println("Cuurent state: "+addToEdit.getStateCd());
			
			HashMap<String, ArrayList<String>> hmCountriesStates = new HashMap<String, ArrayList<String>>();
			
			ArrayList<String> indiasStates = new ArrayList<String>();
			indiasStates.add("Maharashtra");
			indiasStates.add("West Bengal");
			indiasStates.add("Tamil Nadu");
			indiasStates.add("Uttar Pradesh");
			
			ArrayList<String> USAsStates = new ArrayList<String>();
			USAsStates.add("Illinois");
			USAsStates.add("Texas");
			USAsStates.add("Florida");
			USAsStates.add("New York");
			
			ArrayList<String> australiasStates = new ArrayList<String>();
			australiasStates.add("New south wales");
			australiasStates.add("Victoria");
			australiasStates.add("Queensland");
			australiasStates.add("Tasmania");
			
			hmCountriesStates.put("India",indiasStates);
			hmCountriesStates.put("United states of America", USAsStates);
			hmCountriesStates.put("Australia", australiasStates);
			
			String userToEditCountry = addToEdit.getCountryCd();
			int i = 1;
			ArrayList<String> statesOfCountry = hmCountriesStates.get(userToEditCountry);
			for(String perstate: statesOfCountry){
				System.out.println(i+++") "+perstate);
			}
			
			System.out.println("Select the state and enter a number accordingly");
			int userStateChoice = t.nextInt();
			int j1=1
					;
			for(String perstate: statesOfCountry){
				if(j1==userStateChoice){
					addToEdit.setStateCd(perstate);
				}
				j1++;
			}
			break;
			
		case 4://details
			System.out.println("Current details: "+addToEdit.getAddressDetails()+"\nEnter the address details you want to change it to");
			String newAddDetails = t.nextLine();
			addToEdit.setAddressDetails(newAddDetails);
			break;
			
		case 5://pincode
			System.out.println("Current pincode: "+addToEdit.getPinCode()+"\nEnter the new pincode: ");
			String newPincode = t.next();
			addToEdit.setPinCode(newPincode);
			break;
			
		default:
			System.out.println("Kindly enter a valid input");
			break;
		}
	}

}

package operations;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DataObjects.CommTransactionDO;

public class AllCommissions {
	
	public static boolean isValidDate(String date) {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false); // Disable lenient parsing to ensure strict validation
        
        try {
            // Try to parse the date string
            sdf.parse(date);
            return true; // If parsing is successful, it's a valid date
        } catch (ParseException e) {
            return false;
        }
    }
	
	public static Date convertStringToDate(String dateString) {
        // Define the input date format as dd-MM-yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        try {
            // Parse the string to a Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
       
            return null; // Return null if parsing fails
        }
    }
	
	public static boolean checkIfDateBetween(Date start, Date end, Date betweenCheck)
	{
		if(betweenCheck.after(start) && betweenCheck.before(end) 
				|| start.equals(betweenCheck) 
				|| end.equals(betweenCheck))
		{
			return true;
		}
		return false;
	}
	
	public void viewAllCommissions(
			LinkedHashMap<String, List<CommTransactionDO>> LHMCommData) 
	{
		Scanner t = new Scanner(System.in);
		
		System.out.print("Kindly enter the time frame within which you would like to view the exiting transaction. Enter the date in \nStart date: ");
		String startDate = t.next();
		
		System.out.print("End date: ");
		String endDate = t.next();
		
		Date startFormatedDate = new Date();
		Date endFormatedDate = new Date();
		
		boolean startFormatCheck = isValidDate(startDate);
		boolean endFormatCheck = isValidDate(endDate);
		
		//Checks the format of start date
		if(startFormatCheck){
			startFormatedDate = convertStringToDate(startDate+" 00:00:00");
		}else{
			System.out.println("Start date is not in the correct format");
		}
		
		//Checks the format of end date
		if(endFormatCheck){
			endFormatedDate = convertStringToDate(endDate+" 23:59:59");
		}else{
			System.out.println("End date is not in the correct format");
		}
		
		List<CommTransactionDO> commListPerAgent = new ArrayList<CommTransactionDO>();
		
		for(Entry<String, List<CommTransactionDO>> perAgentAndComm: LHMCommData.entrySet())
		{
			commListPerAgent = perAgentAndComm.getValue();
			for(CommTransactionDO perComm: commListPerAgent)
			{
				Date dateToCheck = perComm.getDateTime();
				if(checkIfDateBetween(startFormatedDate,endFormatedDate, dateToCheck))
				{
					System.out.println(perComm.toString());
				}else{
					System.out.println("There are no existing transactions between these two dates");
				}
			}
			break;
		}

		
		
	}
	

}

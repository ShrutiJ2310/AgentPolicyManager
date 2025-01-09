package operations;

public class premiumCalc {
	public double premiumCalTermIns(double sumAssured, double coverAmtOf1Rider, int numOfRiders)
	{

		double eachRider = coverAmtOf1Rider*0.0002;
		double ridersAmountToAdd = 0;
		for(int i =1; i<=numOfRiders; i++){
			ridersAmountToAdd = ridersAmountToAdd+eachRider;
		}
		
		double premium = sumAssured*0.0001 + ridersAmountToAdd;
		return premium;
	}
	
	public double premiumCalTermHealthIns(double sumAssured, double coverAmtOf1Rider, int numOfRiders)
	{
		double eachRider = coverAmtOf1Rider*0.0002;
		double ridersAmountToAdd = 0;
		for(int i =1; i<=numOfRiders; i++){
			ridersAmountToAdd = ridersAmountToAdd+eachRider;
		}
		
		double premium = sumAssured*0.0001*1.25 + ridersAmountToAdd;
		return premium;
	}

}

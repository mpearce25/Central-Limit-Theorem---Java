import java.security.SecureRandom;
import java.util.*;

public class CentralLimitTheorem {

	public static void main(String[] args) {

		Display.initDisplay();

	}

	public static ArrayList<Double> generateRandom(double prob, int sampleSize, int numberSamples) {
		
		ArrayList<Double> means = new ArrayList<Double>();
		
		
		double runningSum = 0.0;
		for (int k = 0; k < numberSamples; k++){
		int workingCars = 0;
		int brokenCars = 0;
		
		for (int i = 0; i < sampleSize; i++){
			SecureRandom rand = new SecureRandom();
			double randomCar = rand.nextDouble();
			if (randomCar < prob){
				//System.out.println("The car is broken");
				workingCars ++;
			}
			else{
				//System.out.println("The car works");
				brokenCars ++;
			}
			
		}
		//System.out.println(workingCars / 64.00);
		
		means.add( (double)workingCars / (double)sampleSize);
		//runningSum += (workingCars/ sampleSize);
		//System.out.println("\n\nNumber of broken cars: " + brokenCars);
		
		}
		
		//System.out.println("\n\n\n" + runningSum / 10000.0);
		
		
		
		
		
		
		
		return means;
	}
}
